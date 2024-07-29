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
import com.snap.business.sdk.v3.model.SendValidationEventRequest;
import com.snap.business.sdk.v3.model.ValidateResponse;
import com.snap.business.sdk.v3.util.ConversionConstants;
import com.snap.business.sdk.v3.util.ConversionUtil;
import com.snap.business.sdk.v3.util.ServerEnv;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversionApiClient {
    private final Logger logger = LoggerFactory.getLogger(ConversionApiClient.class);
    private final DefaultApi capi;
    private final String accessToken;
    private final boolean isDebugEnabled;
    private final String TEST_EVENT_CODE = "test_event_from_bsdk";

    private ConversionApiClient(Builder builder) {
        boolean isLaunchPadEnabled =
                builder.launchPadUrl != null && !builder.launchPadUrl.trim().isEmpty();
        this.isDebugEnabled = builder.isDebugEnabled;
        this.accessToken = builder.accessToken;

        ApiClient client =
                builder.okHttpClient != null
                        ? new ApiClient(builder.okHttpClient)
                        : new ApiClient();
        client.addDefaultHeader(SDK_VER_HEADER, LANG_VERSION);
        client.addDefaultHeader("accept-encoding", "");

        // if there are additional headers, add them to the client.
        if (builder.additionalHeaders != null && !builder.additionalHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : builder.additionalHeaders.entrySet()) {
                client.addDefaultHeader(entry.getKey(), entry.getValue());
            }
        }

        client.setServerIndex(builder.serverEnv.getIndex());

        if (isLaunchPadEnabled) {
            client.setUserAgent(ConversionConstants.USER_AGENT_WITH_PAD)
                    .setBasePath(builder.launchPadUrl.trim());
        } else {
            client.setUserAgent(ConversionConstants.USER_AGENT);
        }

        this.capi = new DefaultApi(client);

        // if custom url is provided, set it as the base url.
        if (builder.customUrl != null && !builder.customUrl.isEmpty()) {
            this.capi.setCustomBaseUrl(builder.customUrl);
        }

        if (isDebugEnabled) {
            logEvent(Level.INFO, "Debug mode is enabled");
        }

        if (builder.isInternalDebugEnabled) {
            this.capi.getApiClient().setDebugging(true);
            logEvent(Level.INFO, "Internal debug mode is enabled");
        }
    }

    /**
     * Synchronously sends a single event to the Snap Conversion API. Use sendEventAsync for
     * asynchronous operation.
     *
     * @param assetId The asset ID to which the event is sent (required), such as Pixel ID or Snap
     *     Pixel ID.
     * @param capiEvent The event data to send (required).
     * @return The EventResponse from the API indicating the result of the operation.
     */
    public EventResponse sendEvent(String assetId, CapiEvent capiEvent) {
        return sendEvents(assetId, Collections.singletonList(capiEvent), false);
    }

    /**
     * Synchronously sends a single test event to the Snap Conversion API. Test events are sent but
     * not processed. Use sendTestEventAsync for asynchronous operation.
     *
     * @param assetId The asset ID to which the event is sent (required), such as Pixel ID or Snap
     *     Pixel ID.
     * @param capiEvent The test event data to send (required).
     * @return The EventResponse from the API indicating the result of the operation.
     */
    public EventResponse sendTestEvent(String assetId, CapiEvent capiEvent) {
        return sendEvents(assetId, Collections.singletonList(capiEvent), true);
    }

    /**
     * Synchronously sends a batch of events to the Snap Conversion API. Use sendEventsBatchAsync
     * for asynchronous operation.
     *
     * @param assetId The asset ID to which the events are sent (required), such as Pixel ID or Snap
     *     Pixel ID.
     * @param capiEvents List of events to send (required).
     * @return The EventResponse from the API indicating the result of the operation.
     */
    public EventResponse sendEventsBatch(String assetId, List<CapiEvent> capiEvents) {
        return sendEvents(assetId, capiEvents, false);
    }

    /**
     * Synchronously sends a batch of test events to the Snap Conversion API. Test events are sent
     * but not processed. Use sendTestEventsBatchAsync for asynchronous operation.
     *
     * @param assetId The asset ID to which the test events are sent (required), such as Pixel ID or
     *     Snap Pixel ID.
     * @param capiEvents List of test events to send (required).
     * @return The EventResponse from the API indicating the result of the operation.
     */
    public EventResponse sendTestEventsBatch(String assetId, List<CapiEvent> capiEvents) {
        return sendEvents(assetId, capiEvents, true);
    }

    /**
     * Send multiple events to Snap Conversion API synchronously.
     *
     * @param assetId - The asset ID to send the event to (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvents - The list of events to send (required)
     * @param isTestEvent - Is this a test event (Test events will be sent, but not processed)
     * @return - The EventResponse from the API
     */
    private EventResponse sendEvents(
            String assetId, List<CapiEvent> capiEvents, boolean isTestEvent) {

        EventResponse result;
        capiEvents.forEach(this::setIntegration);
        try {
            if (assetId == null || assetId.isEmpty()) {
                throw new IllegalArgumentException("Asset ID is required for sending events");
            }

            SendEventRequest request = new SendEventRequest().data(capiEvents);
            if (isTestEvent) {
                request.testEventCode(TEST_EVENT_CODE);
            }

            result = capi.sendEvent(assetId, this.accessToken, request);
            logEvent(
                    result.getStatus() == EventResponse.StatusEnum.VALID
                            ? Level.INFO
                            : Level.WARNING,
                    result.toString());
        } catch (ApiException e) {
            result = ConversionUtil.handleEventException(e);
            logEvent(Level.SEVERE, result.toString());
        } catch (Exception e) {
            result =
                    new EventResponse()
                            .status(EventResponse.StatusEnum.INVALID)
                            .reason(e.getMessage());
            logEvent(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }

        return result;
    }

    /**
     * Asynchronously sends a single event to the Snap Conversion API.
     *
     * @param assetId - The asset ID for the event destination (required).
     * @param capiEvent - The event to be sent (required).
     */
    public void sendEventAsync(String assetId, CapiEvent capiEvent) {
        sendEventsAsync(assetId, Collections.singletonList(capiEvent), false);
    }

    /**
     * Asynchronously sends a single test event to the Snap Conversion API. (Test events are sent
     * but not processed)
     *
     * @param assetId - The asset ID for the event destination (required).
     * @param capiEvent - The event to be sent (required).
     */
    public void sendTestEventAsync(String assetId, CapiEvent capiEvent) {
        sendEventsAsync(assetId, Collections.singletonList(capiEvent), true);
    }

    /**
     * Asynchronously sends a batch of events to the Snap Conversion API.
     *
     * @param assetId - The asset ID for the event destination (required).
     * @param capiEvents - The list of events to be sent (required).
     */
    public void sendEventsBatchAsync(String assetId, List<CapiEvent> capiEvents) {
        sendEventsAsync(
                assetId,
                capiEvents,
                true,
                ConversionUtil.getDefaultCallback(logger, isDebugEnabled));
    }

    /**
     * Asynchronously sends a batch of test events to the Snap Conversion API. (Test events are sent
     * but not processed)
     *
     * @param assetId - The asset ID for the event destination (required).
     * @param capiEvents - The list of events to be sent (required).
     */
    public void sendTestEventsBatchAsync(String assetId, List<CapiEvent> capiEvents) {
        sendEventsAsync(
                assetId,
                capiEvents,
                true,
                ConversionUtil.getDefaultCallback(logger, isDebugEnabled));
    }

    /**
     * Send multiple events to Snap Conversion API Asynchronously
     *
     * @param assetId - The asset ID to send the event to (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvents - The list of events to send (required)
     */
    private void sendEventsAsync(String assetId, List<CapiEvent> capiEvents, boolean isTestEvent) {
        sendEventsAsync(
                assetId,
                capiEvents,
                isTestEvent,
                ConversionUtil.getDefaultCallback(logger, isDebugEnabled));
    }

    private void sendEventsAsync(
            String assetId,
            List<CapiEvent> capiEvents,
            boolean isTestEvent,
            ApiCallback<EventResponse> callback) {

        try {
            if (assetId == null || assetId.isEmpty()) {
                throw new IllegalArgumentException("Asset ID is required for sending events");
            }

            SendEventRequest request = new SendEventRequest().data(capiEvents);
            if (isTestEvent) {
                request.setTestEventCode(TEST_EVENT_CODE);
            }

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
        return sendValidateEventsBatch(assetId, Collections.singletonList(capiEvent));
    }

    /**
     * Send multiple events to Snap Conversion API for validation, these events will not be
     * processed only validated. Results will be available in the Events Manager Test event tool
     *
     * @param assetId - The asset ID to get the logs for (required) (Pixel ID or Snap Pixel ID)
     * @param capiEvents - The list of events to send (required)
     * @return - The ValidateResponse from the API
     */
    public ValidateResponse sendValidateEventsBatch(String assetId, List<CapiEvent> capiEvents) {
        ValidateResponse result;
        capiEvents.forEach(this::setIntegration);

        try {
            SendValidationEventRequest request = new SendValidationEventRequest().data(capiEvents);
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
        private String customUrl = "";
        private Map<String, String> additionalHeaders = new HashMap<>();
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

        public Builder setCustomUrl(String customUrl) {
            this.customUrl = customUrl;
            return this;
        }

        public Builder setAdditionalHeaders(Map<String, String> additionalHeaders) {
            this.additionalHeaders = additionalHeaders;
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
