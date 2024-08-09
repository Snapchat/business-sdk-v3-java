package com.snap.business.sdk.v3.util;


import com.snap.business.sdk.v3.ApiCallback;
import com.snap.business.sdk.v3.ApiException;
import com.snap.business.sdk.v3.model.EventResponse;
import com.snap.business.sdk.v3.model.EventResponse.StatusEnum;
import com.snap.business.sdk.v3.model.ValidateResponse;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.slf4j.Logger;

public class ConversionUtil {

    public static void logEvent(
            Logger logger, String logMessage, Level level, boolean isDebugEnabled) {
        if (!isDebugEnabled) {
            return;
        }

        String msg = "[Snap Business SDK] " + logMessage;
        if (level.equals(Level.INFO)) {
            logger.info(msg);
        } else if (level.equals(Level.WARNING)) {
            logger.warn(msg);
        } else if (level.equals(Level.SEVERE)) {
            logger.error(msg);
        } else {
            logger.debug(msg);
        }
    }

    public static EventResponse handleEventException(ApiException e) {

        EventResponse result;
        try {
            result = EventResponse.fromJson(e.getResponseBody());
            result =
                    result == null
                            ? new EventResponse()
                                    .status(StatusEnum.INVALID)
                                    .reason(e.getResponseBody())
                            : result;
        } catch (Exception ex) {
            result = new EventResponse().status(StatusEnum.INVALID).reason(e.getResponseBody());
        }
        return result;
    }

    public static ValidateResponse handleValidateEventException(ApiException e) {
        ValidateResponse result;
        try {
            result = ValidateResponse.fromJson(e.getResponseBody());
        } catch (Exception ex) {
            result =
                    new ValidateResponse()
                            .status(ValidateResponse.StatusEnum.INVALID)
                            .reason(e.getResponseBody());
        }
        return result;
    }

    public static ApiCallback<EventResponse> getDefaultCallback(
            Logger logger, boolean isDebugEnabled) {
        return new ApiCallback<EventResponse>() {
            @Override
            public void onFailure(
                    ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
                String logMessage =
                        "Event sending failed: "
                                + ConversionUtil.handleEventException(e).toString();
                logEvent(logger, logMessage, Level.SEVERE, isDebugEnabled);
            }

            @Override
            public void onSuccess(
                    EventResponse result,
                    int statusCode,
                    Map<String, List<String>> responseHeaders) {
                Level level = result.getStatus() == StatusEnum.VALID ? Level.INFO : Level.WARNING;
                logEvent(logger, result.toString(), level, isDebugEnabled);
            }

            @Override
            public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {}

            @Override
            public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {}
        };
    }
}
