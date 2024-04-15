package com.snap.business.sdk.v3.examples;


import com.snap.business.sdk.v3.api.ConversionApiClient;
import com.snap.business.sdk.v3.model.CapiEvent;
import com.snap.business.sdk.v3.model.EventResponse;
import com.snap.business.sdk.v3.util.ServerEnv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEventsExample {
    private static final Logger logger = LoggerFactory.getLogger(SendEventsExample.class);
    private static final ServerEnv SERVER_ENV = ServerEnv.STAGING;

    public static void main(String[] args) {
        final Dispatcher dispatcher = new Dispatcher(Executors.newCachedThreadPool());
        dispatcher.setMaxRequests(32);
        dispatcher.setMaxRequestsPerHost(32);

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .dispatcher(dispatcher)
                        .connectionPool(new ConnectionPool(32, 30, TimeUnit.SECONDS))
                        .writeTimeout(15, TimeUnit.SECONDS) // 10s by default
                        .connectTimeout(15, TimeUnit.SECONDS) // 10s by default
                        .retryOnConnectionFailure(true)
                        .build();

        ConversionApiClient capiClient =
                new ConversionApiClient.Builder()
                        .setAccessToken(CapiEventExampleBuilder.CAPI_TOKEN)
                        .setServerEnv(SERVER_ENV)
                        .setOkHttpClient(okHttpClient)
                        .withDebuggingEnabled()
                        .withInternalDebuggingEnabled()
                        .build();

        // Events
        CapiEvent capiPixelEvent = CapiEventExampleBuilder.getPixelEvent();
        CapiEvent capiAppEvent = CapiEventExampleBuilder.getAppEvent();
        String testAssetId = CapiEventExampleBuilder.TEST_ASSET_ID;

        // Batch Events
        List<CapiEvent> capiBatchEvents =
                new ArrayList<>(Arrays.asList(capiPixelEvent, capiAppEvent));

        // Sending single event
        logger.info("------- Sending Single Event to CAPI -----------");
        EventResponse apiResponse = capiClient.sendEvent(testAssetId, capiPixelEvent);
        logger.info("--> Status: " + apiResponse.getStatus());
        logger.info("-> Reason: {}", apiResponse.getReason());
        logger.info("-> errors {}", apiResponse.getErrors());

        // Sending test event
        apiResponse = capiClient.sendTestEvent(testAssetId, capiPixelEvent);
        logger.info("--> Status: " + apiResponse.getStatus());

        logger.info("------- Sending Batch Events to CAPI -----------");
        EventResponse apiResponseBatch = capiClient.sendEventsBatch(testAssetId, capiBatchEvents);
        logger.info("--> Status: " + apiResponseBatch.getStatus());
        logger.info("-> errors {}", apiResponse.getErrors());
        logger.info("-> Reason: {}", apiResponse.getReason());

        // Sending test batch event
        apiResponseBatch = capiClient.sendTestEventsBatch(testAssetId, capiBatchEvents);
        logger.info("--> Status: " + apiResponseBatch.getStatus());
    }
}
