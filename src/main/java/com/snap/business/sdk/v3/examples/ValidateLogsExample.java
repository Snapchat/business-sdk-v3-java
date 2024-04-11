package com.snap.business.sdk.v3.examples;


import com.snap.business.sdk.v3.api.ConversionApiClient;
import com.snap.business.sdk.v3.model.GetLogsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateLogsExample {
    private static final Logger logger = LoggerFactory.getLogger(ValidateLogsExample.class);

    public static void main(String[] args) {

        ConversionApiClient capiClient =
                new ConversionApiClient.Builder()
                        .setAccessToken(CapiEventExampleBuilder.CAPI_TOKEN)
                        .setServerEnv(CapiEventExampleBuilder.SERVER_ENV)
                        // .setOkHttpClient(null) // if you want to use custom OkHttpClient
                        .withDebuggingEnabled() // if you want to enable debugging from events
                        // .withInternalDebuggingEnabled() // if you want to enable internal
                        // debugging from API calls
                        .build();

        System.out.println("------- Getting Validate Event Logs -----------");
        GetLogsResponse getLogsResponse =
                capiClient.getValidateEventLogs(CapiEventExampleBuilder.TEST_ASSET_ID);
        logger.info("Status: " + getLogsResponse.getStatus());
        logger.info("Reason: " + getLogsResponse.getReason());
        logger.info("Logs: " + getLogsResponse.getLogs());
    }
}
