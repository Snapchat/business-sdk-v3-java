package com.snap.business.sdk.v3.api;

import static com.snap.business.sdk.v3.util.ConversionConstants.LANG_VERSION;
import static com.snap.business.sdk.v3.util.ConversionConstants.SDK_VER_HEADER;

import com.snap.business.sdk.v3.ApiCallback;
import com.snap.business.sdk.v3.ApiClient;
import com.snap.business.sdk.v3.ApiException;
import com.snap.business.sdk.v3.model.CapiEvent;
import com.snap.business.sdk.v3.model.EventResponse;
import com.snap.business.sdk.v3.model.GetLogsResponse;
import com.snap.business.sdk.v3.model.GetStatsResponse;
import com.snap.business.sdk.v3.model.SendEventRequest;
import com.snap.business.sdk.v3.model.ValidateResponse;
import com.snap.business.sdk.v3.util.ConversionConstants;
import com.snap.business.sdk.v3.util.ConversionUtil;
import com.snap.business.sdk.v3.util.ServerEnv;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversionApiClient {
    private final Logger logger = LoggerFactory.getLogger(ConversionApiClient.class);
    private final DefaultApi capi;
    private final String accessToken;
    private final boolean isDebugEnabled;

    private ConversionApiClient(Builder builder) {
        boolean isLaunchPadEnabled =
                builder.launchPadUrl != null && !builder.launchPadUrl.trim().isEmpty();
        this.isDebugEnabled = builder.isDebugEnabled;

        if (builder.accessToken == null || builder.accessToken.trim().isEmpty()) {
            throw new IllegalArgumentException("Access Token is required");
        }

        this.accessToken = builder.accessToken;

        ApiClient client =
                builder.okHttpClient != null
                        ? new ApiClient(builder.okHttpClient)
                        : new ApiClient();
        client.addDefaultHeader(SDK_VER_HEADER, LANG_VERSION);
        client.addDefaultHeader("accept-encoding", "");
        client.setServerIndex(builder.serverEnv.getIndex());

        if (isLaunchPadEnabled) {
            client.setUserAgent("User-Agent-With-LaunchPad")
                    .setBasePath(builder.launchPadUrl.trim());
        } else {
            client.setUserAgent("Standard-User-Agent");
        }

        this.capi = new DefaultApi(client);

        if (isDebugEnabled) {
            logger.info("[Snap Business SDK] Debug mode is enabled");
        }

        if (builder.isInternalDebugEnabled) {
            this.capi.getApiClient().setDebugging(true);
            logger.info("[Snap Business SDK] Internal debug mode is enabled");
        }
    }

    /**
     * Send a single event to Snap Conversion API synchronously For Asynchronous events, use
     * sendEventAsync
     *
     * @param assetId - The asset ID to send the event to (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvent - The event to send (required)
     * @return - The EventResponse from the API
     */
    public EventResponse sendEvent(String assetId, CapiEvent capiEvent) {
        return sendEvents(assetId, Collections.singletonList(capiEvent));
    }

    /**
     * Send multiple events to Snap Conversion API synchronously For Asynchronous events, use
     * sendEventsAsync
     *
     * @param assetId - The asset ID to send the event to (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvents - The list of events to send (required)
     * @return - The EventResponse from the API
     */
    public EventResponse sendEvents(String assetId, List<CapiEvent> capiEvents) {

        EventResponse result;
        capiEvents.forEach(this::setIntegration);
        try {
            SendEventRequest request = new SendEventRequest().data(capiEvents);

            result = capi.sendEvent(assetId, this.accessToken, request);
            logEvent(
                    result.getStatus() == EventResponse.StatusEnum.VALID
                            ? Level.INFO
                            : Level.WARNING,
                    result.toString());
        } catch (ApiException e) {
            logger.info("API Exception: " + e.getMessage() + " - " + e.getCode());
            result = ConversionUtil.handleEventException(e);
            logEvent(Level.SEVERE, result.toString());
        } catch (Exception e) {
            logger.info("Exception: " + e.getMessage() + " " + e.getStackTrace());
            result =
                    new EventResponse()
                            .status(EventResponse.StatusEnum.INVALID)
                            .reason(e.getMessage());
            logEvent(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }

        return result;
    }

    /**
     * Send a single event to Snap Conversion API Asynchronously
     *
     * @param assetId - The asset ID to send the event to (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvent - The event to send (required)
     */
    public void sendEventAsync(String assetId, CapiEvent capiEvent) {
        sendEventsAsync(assetId, Collections.singletonList(capiEvent));
    }

    /**
     * Send multiple events to Snap Conversion API Asynchronously
     *
     * @param assetId - The asset ID to send the event to (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvents - The list of events to send (required)
     */
    public void sendEventsAsync(String assetId, List<CapiEvent> capiEvents) {
        sendEventsAsync(
                assetId, capiEvents, ConversionUtil.getDefaultCallback(logger, isDebugEnabled));
    }

    private void sendEventsAsync(
            String assetId, List<CapiEvent> capiEvents, ApiCallback<EventResponse> callback) {
        SendEventRequest request = new SendEventRequest().data(capiEvents);
        try {
            capi.sendEventAsync(assetId, this.accessToken, request, callback);
        } catch (Exception e) {
            logEvent(Level.SEVERE, "Failed to send events asynchronously: " + e.getMessage());
        }
    }

    /**
     * Send a single event to Snap Conversion API for validation, these events will not be processed
     * only validated. Results will be available in the Events Manager Test event tool
     *
     * @param assetId - The asset ID to get the logs for (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvent - The event to send (required)
     * @return - The ValidateResponse from the API
     */
    public ValidateResponse sendValidateEvent(String assetId, CapiEvent capiEvent) {
        return sendValidateEvents(assetId, Collections.singletonList(capiEvent));
    }

    /**
     * Send multiple events to Snap Conversion API for validation, these events will not be
     * processed only validated. Results will be available in the Events Manager Test event tool
     *
     * @param assetId - The asset ID to get the logs for (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvents - The list of events to send (required)
     * @return - The ValidateResponse from the API
     */
    public ValidateResponse sendValidateEvents(String assetId, List<CapiEvent> capiEvents) {
        ValidateResponse result;
        capiEvents.forEach(this::setIntegration);

        try {
            SendEventRequest request = new SendEventRequest().data(capiEvents);
            result = capi.sendValidationEvent(assetId, this.accessToken, request);
            logEvent(Level.INFO, result.toString());
        } catch (ApiException e) {
            result = ConversionUtil.handleValidateEventException(e);
            logEvent(Level.SEVERE, result.toString());
        } catch (Exception e) {
            result =
                    new ValidateResponse()
                            .status(ValidateResponse.StatusEnum.INVALID)
                            .reason(e.getMessage());
            logEvent(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    /**
     * Get the validate logs for a specific asset ID for the past 24 hours.
     *
     * @param assetId - The asset ID to get the logs for (required) (Pixel ID or Snap Pixel ID)
     * @return - A GetLogsResponse with logs
     */
    public GetLogsResponse getValidateEventLogs(String assetId) {
        GetLogsResponse result;
        try {
            result = capi.getValidationLogs(assetId, this.accessToken);
            logEvent(
                    result.getStatus() == GetLogsResponse.StatusEnum.SUCCESS
                            ? Level.INFO
                            : Level.WARNING,
                    result.toString());
        } catch (Exception e) {
            result =
                    new GetLogsResponse()
                            .status(GetLogsResponse.StatusEnum.FAILURE)
                            .reason(e.getMessage());
            logEvent(Level.SEVERE, "FAILURE " + e.getMessage());
        }
        return result;
    }

    /**
     * Get the validate stats for a specific asset ID for the past 24 hours.
     *
     * @param assetId - The asset ID to get the stats for (required) (Pixel ID or Snap Pixel ID)
     * @return - A GetStatsResponse with stats
     */
    public GetStatsResponse getValidateEventStats(String assetId) {
        GetStatsResponse result;
        try {
            result = capi.getValidationStats(assetId, this.accessToken);
            logEvent(Level.INFO, result.toString());
        } catch (Exception e) {
            result =
                    new GetStatsResponse()
                            .status(GetStatsResponse.StatusEnum.FAILURE)
                            .reason(e.getMessage());
            logEvent(Level.SEVERE, "FAILURE " + e.getMessage());
        }
        return result;
    }

    /**
     * Helper function to log events
     *
     * @param level - The log level
     * @param logMessage - The message to log
     */
    private void logEvent(Level level, String logMessage) {
        ConversionUtil.logEvent(logger, logMessage, level, isDebugEnabled);
    }

    /**
     * Helper function to set the integration field in the CAPI event when it is not provided.
     *
     * @param capiEvent - The CAPI event to set the integration field for
     */
    private void setIntegration(CapiEvent capiEvent) {
        String integration = capiEvent.getIntegration();
        if (integration == null || integration.isEmpty()) {
            capiEvent.integration(ConversionConstants.INTEGRATION_SDK);
        }
        logEvent(Level.INFO, capiEvent.toString());
    }

    /** Builder class for ConversionApiClient */
    public static class Builder {
        private String accessToken = "";
        private String launchPadUrl = "";
        private OkHttpClient okHttpClient = null;
        private ServerEnv serverEnv = ServerEnv.PROD;
        private boolean isDebugEnabled = false;
        private boolean isInternalDebugEnabled = false;

        public Builder() {}

        /**
         * Set the Capi Token for the client to use for authentication.
         *
         * @param accessToken - The long lived token to use for authentication
         */
        public Builder setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder setLaunchPadUrl(String launchPadUrl) {
            this.launchPadUrl = launchPadUrl;
            return this;
        }

        public Builder setOkHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public Builder setServerEnv(ServerEnv serverEnv) {
            this.serverEnv = serverEnv;
            return this;
        }

        public Builder withDebuggingEnabled() {
            this.isDebugEnabled = true;
            return this;
        }

        public Builder withInternalDebuggingEnabled() {
            this.isInternalDebugEnabled = true;
            return this;
        }

        public ConversionApiClient build() {
            return new ConversionApiClient(this);
        }
    }
}
