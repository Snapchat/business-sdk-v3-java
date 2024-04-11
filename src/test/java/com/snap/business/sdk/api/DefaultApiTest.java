/*
 * Snap Conversions API V3
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1.0.0
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.snap.business.sdk.api;


import com.snap.business.sdk.ApiException;
import com.snap.business.sdk.model.EventResponse;
import com.snap.business.sdk.model.GetLogsResponse;
import com.snap.business.sdk.model.GetStatsResponse;
import com.snap.business.sdk.model.SendEventRequest;
import com.snap.business.sdk.model.ValidateResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** API tests for DefaultApi */
@Disabled
public class DefaultApiTest {

    private final DefaultApi api = new DefaultApi();

    /** @throws ApiException if the Api call fails */
    @Test
    public void getValidationLogsTest() throws ApiException {
        String assetId = null;
        String accessToken = null;
        GetLogsResponse response = api.getValidationLogs(assetId, accessToken);
        // TODO: test validations
    }

    /** @throws ApiException if the Api call fails */
    @Test
    public void getValidationStatsTest() throws ApiException {
        String assetId = null;
        String accessToken = null;
        GetStatsResponse response = api.getValidationStats(assetId, accessToken);
        // TODO: test validations
    }

    /** @throws ApiException if the Api call fails */
    @Test
    public void sendEventTest() throws ApiException {
        String assetId = null;
        SendEventRequest body = null;
        String accessToken = null;
        EventResponse response = api.sendEvent(assetId, accessToken, body);
        // TODO: test validations
    }

    /** @throws ApiException if the Api call fails */
    @Test
    public void sendValidationEventTest() throws ApiException {
        String assetId = null;
        SendEventRequest body = null;
        String accessToken = null;
        ValidateResponse response = api.sendValidationEvent(assetId, accessToken, body);
        // TODO: test validations
    }
}
