package com.snap.business.sdk.examples;


import com.snap.business.sdk.api.ConversionApiClient;
import com.snap.business.sdk.model.CapiEvent;
import com.snap.business.sdk.model.EventResponse;
import com.snap.business.sdk.util.ServerEnv;
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
    private static final String CAPI_TOKEN = "ENTER_VALID_TOKEN";
    private static final String TEST_ASSET_ID = "4a018335-717f-41dd-a66e-58eb2473aa61";
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
                        .setAccessToken(CAPI_TOKEN)
                        .setServerEnv(SERVER_ENV)
                        .setOkHttpClient(okHttpClient)
                        .withDebuggingEnabled()
                        .withInternalDebuggingEnabled()
                        .build();

        // Events
        CapiEvent capiPixelEvent = CapiEventExampleBuilder.getPixelEvent();
        CapiEvent capiAppEvent = CapiEventExampleBuilder.getAppEvent();

        // Batch Events
        List<CapiEvent> capiBatchEvents =
                new ArrayList<>(Arrays.asList(capiPixelEvent, capiAppEvent));

        System.out.println("------- Sending Single Event to CAPI -----------");
        EventResponse apiResponse = capiClient.sendEvent(TEST_ASSET_ID, capiPixelEvent);
        logger.info("--> Status: " + apiResponse.getStatus());
        if (apiResponse.getStatus() == EventResponse.StatusEnum.INVALID) {
            logger.info("-> errors {}", apiResponse.getErrors());
        } else {
            logger.info("-> Reason: {}", apiResponse.getReason());
        }

        System.out.println("------- Sending Batch Event to CAPI -----------");
        EventResponse apiResponseBatch = capiClient.sendEvents(TEST_ASSET_ID, capiBatchEvents);
        logger.info("--> Status: " + apiResponseBatch.getStatus());
        // logger.info("Batch Event sent to CAPI: " + capiBatchEvents.toString());
        if (apiResponse.getStatus() == EventResponse.StatusEnum.INVALID) {
            System.out.println(apiResponse.getErrors());
            logger.info("-> errors {}", apiResponse.getErrors());
        } else {
            logger.info("-> Reason: {}", apiResponse.getReason());
        }
    }
}
