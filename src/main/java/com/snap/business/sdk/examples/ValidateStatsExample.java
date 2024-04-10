package com.snap.business.sdk.examples;


import com.snap.business.sdk.api.ConversionApiClient;
import com.snap.business.sdk.model.GetStatsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidateStatsExample {
    private static final Logger logger = LoggerFactory.getLogger(ValidateStatsExample.class);

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

        System.out.println("------- Getting Validate Event Stats -----------");
        GetStatsResponse getStatsResponse =
                capiClient.getValidateEventStats(CapiEventExampleBuilder.TEST_ASSET_ID);
        logger.info("Status: " + getStatsResponse.getStatus());
        logger.info("Reason: " + getStatsResponse.getReason());
        logger.info("Stats: " + getStatsResponse.getStats());
    }
}
