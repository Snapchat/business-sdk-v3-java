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
                        .build();

        System.out.println("------- Getting Validate Event Logs -----------");
        GetLogsResponse getLogsResponse =
                capiClient.getValidateEventLogs(CapiEventExampleBuilder.TEST_ASSET_ID);
        logger.info("Status: " + getLogsResponse.getStatus());
        logger.info("Reason: " + getLogsResponse.getReason());
        logger.info("Logs: " + getLogsResponse.getLogs());
    }
}
