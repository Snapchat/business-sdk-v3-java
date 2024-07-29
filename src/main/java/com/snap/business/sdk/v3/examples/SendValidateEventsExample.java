package com.snap.business.sdk.v3.examples;


import com.snap.business.sdk.v3.api.ConversionApiClient;
import com.snap.business.sdk.v3.model.CapiEvent;
import com.snap.business.sdk.v3.model.ValidateResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendValidateEventsExample {
    private static final Logger logger = LoggerFactory.getLogger(SendValidateEventsExample.class);

    public static void main(String[] args) {
        ConversionApiClient capiClient =
                new ConversionApiClient.Builder()
                        .setAccessToken(CapiEventExampleBuilder.CAPI_TOKEN)
                        .setServerEnv(CapiEventExampleBuilder.SERVER_ENV)
                        .withDebuggingEnabled()
                        .withInternalDebuggingEnabled()
                        .build();

        // Events
        CapiEvent capiPixelEvent = CapiEventExampleBuilder.getPixelEvent();
        CapiEvent capiAppEvent = CapiEventExampleBuilder.getAppEvent();

        List<CapiEvent> capiBatchEvents =
                new ArrayList<>(Arrays.asList(capiPixelEvent, capiAppEvent));
        logger.info("------- Sending Single Event to CAPI -----------");
        ValidateResponse apiResponseValidate =
                capiClient.sendValidateEvent(CapiEventExampleBuilder.TEST_ASSET_ID, capiPixelEvent);
        logger.info("Status: " + apiResponseValidate.getStatus());
        logger.info("Reason: " + apiResponseValidate.getReason());
        logger.info("Logs: " + apiResponseValidate.getEventLogs());

        ValidateResponse batchResponse =
                capiClient.sendValidateEventsBatch(
                        CapiEventExampleBuilder.TEST_ASSET_ID, capiBatchEvents);
        logger.info("Status: " + batchResponse.getStatus());
        logger.info("Reason: " + batchResponse.getReason());
        logger.info("Logs: " + batchResponse.getEventLogs());
    }
}
