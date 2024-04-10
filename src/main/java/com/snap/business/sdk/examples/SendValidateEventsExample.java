package com.snap.business.sdk.examples;


import com.snap.business.sdk.api.ConversionApiClient;
import com.snap.business.sdk.model.CapiEvent;
import com.snap.business.sdk.model.ValidateResponse;
import com.snap.business.sdk.util.ServerEnv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendValidateEventsExample {
    private static final Logger logger = LoggerFactory.getLogger(SendValidateEventsExample.class);
    private static final String CAPI_TOKEN = "ENTER_VALID_TOKEN";
    private static final String TEST_ASSET_ID = "4a018335-717f-41dd-a66e-58eb2473aa61";
    private static final ServerEnv SERVER_ENV = ServerEnv.STAGING;

    public static void main(String[] args) {
        ConversionApiClient capiClient =
                new ConversionApiClient.Builder()
                        .setAccessToken(CAPI_TOKEN)
                        .setServerEnv(SERVER_ENV)
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
                capiClient.sendValidateEvent(TEST_ASSET_ID, capiPixelEvent);
        logger.info("Status: " + apiResponseValidate.getStatus());
        logger.info("Reason: " + apiResponseValidate.getReason());
        logger.info("Logs: " + apiResponseValidate.getEventLogs());

        ValidateResponse batchResponse =
                capiClient.sendValidateEvents(TEST_ASSET_ID, capiBatchEvents);
        logger.info("Status: " + batchResponse.getStatus());
        logger.info("Reason: " + batchResponse.getReason());
        logger.info("Logs: " + batchResponse.getEventLogs());
    }
}
