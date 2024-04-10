package com.snap.business.sdk.util;

public class ConversionConstants {
    public static final String SDK_LANGUAGE = "java";
    public static final String SDK_VERSION = "1.0.0";
    public static final String USER_AGENT = String.format("BusinessSDK/Java/%s", SDK_VERSION);
    public static final String USER_AGENT_WITH_PAD = USER_AGENT + " (LaunchPAD)";
    public static final String SDK_VER_HEADER = "X-CAPI-V3-BusinessSDK";
    public static final String LANG_VERSION = String.format("%s/%s", SDK_LANGUAGE, SDK_VERSION);
    public static final String INTEGRATION_SDK = "business-sdk-v3";
}
