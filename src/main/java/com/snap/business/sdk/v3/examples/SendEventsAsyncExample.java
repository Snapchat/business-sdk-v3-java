package com.snap.business.sdk.v3.examples;


import com.snap.business.sdk.v3.api.ConversionApiClient;
import com.snap.business.sdk.v3.model.CapiEvent;
import com.snap.business.sdk.v3.util.ServerEnv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEventsAsyncExample {
    private static final Logger logger = LoggerFactory.getLogger(SendEventsAsyncExample.class);
    private static final ServerEnv SERVER_ENV = ServerEnv.STAGING;

    public static void main(String[] args) {
        ConversionApiClient capiClient =
                new ConversionApiClient.Builder()
                        .setAccessToken(CapiEventExampleBuilder.CAPI_TOKEN)
                        .setServerEnv(SERVER_ENV)
                        .withDebuggingEnabled()
                        .withInternalDebuggingEnabled()
                        .build();

        CapiEvent capiPixelEvent = CapiEventExampleBuilder.getPixelEvent();
        List<CapiEvent> capiBatchEvents = new ArrayList<>(Arrays.asList(capiPixelEvent));

        // Sending Live Async Event
        capiClient.sendEventAsync(CapiEventExampleBuilder.TEST_ASSET_ID, capiPixelEvent);

        // Sending Test Async Event
        capiClient.sendTestEventAsync(CapiEventExampleBuilder.TEST_ASSET_ID, capiPixelEvent);

        // Sending Live Async Batch Events
        capiClient.sendEventsBatchAsync(CapiEventExampleBuilder.TEST_ASSET_ID, capiBatchEvents);

        // Sending Test Async Batch Events
        capiClient.sendTestEventsBatchAsync(CapiEventExampleBuilder.TEST_ASSET_ID, capiBatchEvents);
    }
}
