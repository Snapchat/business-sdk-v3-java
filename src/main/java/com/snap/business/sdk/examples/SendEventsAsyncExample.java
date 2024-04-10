package com.snap.business.sdk.examples;


import com.snap.business.sdk.api.ConversionApiClient;
import com.snap.business.sdk.model.CapiEvent;
import com.snap.business.sdk.util.ServerEnv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEventsAsyncExample {
    private static final Logger logger = LoggerFactory.getLogger(SendEventsAsyncExample.class);
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

        CapiEvent capiPixelEvent = CapiEventExampleBuilder.getPixelEvent();

        capiClient.sendEventAsync(TEST_ASSET_ID, capiPixelEvent);
    }
}
