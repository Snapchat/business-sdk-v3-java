package com.snap.business.sdk.v3.examples;


import com.snap.business.sdk.v3.model.AppData;
import com.snap.business.sdk.v3.model.CapiEvent;
import com.snap.business.sdk.v3.model.CustomData;
import com.snap.business.sdk.v3.model.UserData;
import com.snap.business.sdk.v3.util.ServerEnv;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CapiEventExampleBuilder {

    public static final String CAPI_TOKEN = "ENTER_VALID_TOKEN";
    public static final String TEST_ASSET_ID = "ENTER_VALID_ASSET_ID";
    public static final ServerEnv SERVER_ENV = ServerEnv.STAGING;

    public static CapiEvent getPixelEvent() {
        CapiEvent capiEvent = new CapiEvent();
        capiEvent.setEventName("purchase");
        capiEvent.setEventTime(1712009009L);
        capiEvent.setActionSource("web");
        capiEvent.dataProcessingOptions(Collections.singletonList("LDU"));
        capiEvent.setAssetId(TEST_ASSET_ID);

        UserData userData = new UserData();
        userData.setEm(
                Collections.singletonList(
                        "7b17fb0bd173f625b58636fb796407c22b3d16fc78302d79f0fd30c2fc2fc068"));
        userData.setExternalId("x");
        userData.setSubscriptionId("x");
        userData.setLeadId("x");
        userData.setAnonId(Arrays.asList("ANON_ID_1", "ANON_ID_2"));
        userData.setDownloadId("x");
        userData.setPartnerId("Axxxxxxxxx");
        capiEvent.setUserData(userData);

        CustomData customData = new CustomData();
        customData.setCurrency("USD");
        customData.setValue(BigDecimal.valueOf(142.52));
        customData.setContentName("Test Content");
        customData.setPredictedLtv(BigDecimal.valueOf(100.00));
        customData.setDestinationIds(new ArrayList<>(Arrays.asList("123e4567-e89b-12d3-a456-426614174000", "123e4567-e89b-12d3-a456-426614174001")));
        customData.setEventTag("test_event_tag");
        capiEvent.setCustomData(customData);

        return capiEvent;
    }

    public static CapiEvent getAppEvent() {
        CapiEvent capiEvent = new CapiEvent();
        capiEvent.setEventName("purchase");
        capiEvent.setEventTime(1712009009L);
        capiEvent.setActionSource("web");
        capiEvent.dataProcessingOptions(Collections.singletonList("LDU"));
        capiEvent.setTestEventCode("test_event_code");

        AppData appData = new AppData();
        appData.setAppId("com.snapchat.android");
        appData.setAdvertiserTrackingEnabled(true);
        appData.setExtinfo(
                new ArrayList<>(
                        Arrays.asList(
                                "a2",
                                "com.some.app",
                                "771",
                                "Version 7.7.1",
                                "10.1.1",
                                "OnePlus6",
                                "en_US",
                                "GMT-1",
                                "TMobile",
                                "1920",
                                "1080",
                                "2.00",
                                "2",
                                "128",
                                "8",
                                "USA/New York")));
        capiEvent.setAppData(appData);

        UserData userData = new UserData();
        userData.setEm(
                Collections.singletonList(
                        "7b17fb0bd173f625b58636fb796407c22b3d16fc78302d79f0fd30c2fc2fc068"));
        capiEvent.setUserData(userData);

        CustomData customData = new CustomData();
        customData.setCurrency("USD");
        customData.setValue(BigDecimal.valueOf(142.52));
        customData.setEventTag("test_event_tag");
        capiEvent.setCustomData(customData);
        return capiEvent;
    }
}
