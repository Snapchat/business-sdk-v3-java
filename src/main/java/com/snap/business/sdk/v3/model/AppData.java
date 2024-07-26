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


package com.snap.business.sdk.v3.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.snap.business.sdk.v3.JSON;

/**
 * AppData
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", comments = "Generator version: 7.7.0")
public class AppData {
  public static final String SERIALIZED_NAME_EXTINFO = "extinfo";
  @SerializedName(SERIALIZED_NAME_EXTINFO)
  private List<String> extinfo = new ArrayList<>();

  public static final String SERIALIZED_NAME_CAMPAIGN_IDS = "campaign_ids";
  @SerializedName(SERIALIZED_NAME_CAMPAIGN_IDS)
  private String campaignIds;

  public static final String SERIALIZED_NAME_INSTALL_REFERRER = "install_referrer";
  @SerializedName(SERIALIZED_NAME_INSTALL_REFERRER)
  private String installReferrer;

  public static final String SERIALIZED_NAME_INSTALLER_PACKAGE = "installer_package";
  @SerializedName(SERIALIZED_NAME_INSTALLER_PACKAGE)
  private String installerPackage;

  public static final String SERIALIZED_NAME_URL_SCHEMES = "url_schemes";
  @SerializedName(SERIALIZED_NAME_URL_SCHEMES)
  private List<String> urlSchemes = new ArrayList<>();

  public static final String SERIALIZED_NAME_WINDOWS_ATTRIBUTION_ID = "windows_attribution_id";
  @SerializedName(SERIALIZED_NAME_WINDOWS_ATTRIBUTION_ID)
  private String windowsAttributionId;

  public static final String SERIALIZED_NAME_APP_ID = "app_id";
  @SerializedName(SERIALIZED_NAME_APP_ID)
  private String appId;

  public static final String SERIALIZED_NAME_ADVERTISER_TRACKING_ENABLED = "advertiser_tracking_enabled";
  @SerializedName(SERIALIZED_NAME_ADVERTISER_TRACKING_ENABLED)
  private Boolean advertiserTrackingEnabled;

  public AppData() {
  }

  public AppData extinfo(List<String> extinfo) {
    this.extinfo = extinfo;
    return this;
  }

  public AppData addExtinfoItem(String extinfoItem) {
    if (this.extinfo == null) {
      this.extinfo = new ArrayList<>();
    }
    this.extinfo.add(extinfoItem);
    return this;
  }

  /**
   * Get extinfo
   * @return extinfo
   */
  @javax.annotation.Nullable
  public List<String> getExtinfo() {
    return extinfo;
  }

  public void setExtinfo(List<String> extinfo) {
    this.extinfo = extinfo;
  }


  public AppData campaignIds(String campaignIds) {
    this.campaignIds = campaignIds;
    return this;
  }

  /**
   * The campaign IDs for the app.
   * @return campaignIds
   */
  @javax.annotation.Nullable
  public String getCampaignIds() {
    return campaignIds;
  }

  public void setCampaignIds(String campaignIds) {
    this.campaignIds = campaignIds;
  }


  public AppData installReferrer(String installReferrer) {
    this.installReferrer = installReferrer;
    return this;
  }

  /**
   * The install referrer for the app.
   * @return installReferrer
   */
  @javax.annotation.Nullable
  public String getInstallReferrer() {
    return installReferrer;
  }

  public void setInstallReferrer(String installReferrer) {
    this.installReferrer = installReferrer;
  }


  public AppData installerPackage(String installerPackage) {
    this.installerPackage = installerPackage;
    return this;
  }

  /**
   * The installer package for the app.
   * @return installerPackage
   */
  @javax.annotation.Nullable
  public String getInstallerPackage() {
    return installerPackage;
  }

  public void setInstallerPackage(String installerPackage) {
    this.installerPackage = installerPackage;
  }


  public AppData urlSchemes(List<String> urlSchemes) {
    this.urlSchemes = urlSchemes;
    return this;
  }

  public AppData addUrlSchemesItem(String urlSchemesItem) {
    if (this.urlSchemes == null) {
      this.urlSchemes = new ArrayList<>();
    }
    this.urlSchemes.add(urlSchemesItem);
    return this;
  }

  /**
   * Get urlSchemes
   * @return urlSchemes
   */
  @javax.annotation.Nullable
  public List<String> getUrlSchemes() {
    return urlSchemes;
  }

  public void setUrlSchemes(List<String> urlSchemes) {
    this.urlSchemes = urlSchemes;
  }


  public AppData windowsAttributionId(String windowsAttributionId) {
    this.windowsAttributionId = windowsAttributionId;
    return this;
  }

  /**
   * The Windows attribution ID for the app.
   * @return windowsAttributionId
   */
  @javax.annotation.Nullable
  public String getWindowsAttributionId() {
    return windowsAttributionId;
  }

  public void setWindowsAttributionId(String windowsAttributionId) {
    this.windowsAttributionId = windowsAttributionId;
  }


  public AppData appId(String appId) {
    this.appId = appId;
    return this;
  }

  /**
   * The app ID for the app.
   * @return appId
   */
  @javax.annotation.Nullable
  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }


  public AppData advertiserTrackingEnabled(Boolean advertiserTrackingEnabled) {
    this.advertiserTrackingEnabled = advertiserTrackingEnabled;
    return this;
  }

  /**
   * Whether advertiser tracking is enabled for the app.
   * @return advertiserTrackingEnabled
   */
  @javax.annotation.Nullable
  public Boolean getAdvertiserTrackingEnabled() {
    return advertiserTrackingEnabled;
  }

  public void setAdvertiserTrackingEnabled(Boolean advertiserTrackingEnabled) {
    this.advertiserTrackingEnabled = advertiserTrackingEnabled;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppData appData = (AppData) o;
    return Objects.equals(this.extinfo, appData.extinfo) &&
        Objects.equals(this.campaignIds, appData.campaignIds) &&
        Objects.equals(this.installReferrer, appData.installReferrer) &&
        Objects.equals(this.installerPackage, appData.installerPackage) &&
        Objects.equals(this.urlSchemes, appData.urlSchemes) &&
        Objects.equals(this.windowsAttributionId, appData.windowsAttributionId) &&
        Objects.equals(this.appId, appData.appId) &&
        Objects.equals(this.advertiserTrackingEnabled, appData.advertiserTrackingEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(extinfo, campaignIds, installReferrer, installerPackage, urlSchemes, windowsAttributionId, appId, advertiserTrackingEnabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppData {\n");
    sb.append("    extinfo: ").append(toIndentedString(extinfo)).append("\n");
    sb.append("    campaignIds: ").append(toIndentedString(campaignIds)).append("\n");
    sb.append("    installReferrer: ").append(toIndentedString(installReferrer)).append("\n");
    sb.append("    installerPackage: ").append(toIndentedString(installerPackage)).append("\n");
    sb.append("    urlSchemes: ").append(toIndentedString(urlSchemes)).append("\n");
    sb.append("    windowsAttributionId: ").append(toIndentedString(windowsAttributionId)).append("\n");
    sb.append("    appId: ").append(toIndentedString(appId)).append("\n");
    sb.append("    advertiserTrackingEnabled: ").append(toIndentedString(advertiserTrackingEnabled)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("extinfo");
    openapiFields.add("campaign_ids");
    openapiFields.add("install_referrer");
    openapiFields.add("installer_package");
    openapiFields.add("url_schemes");
    openapiFields.add("windows_attribution_id");
    openapiFields.add("app_id");
    openapiFields.add("advertiser_tracking_enabled");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to AppData
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!AppData.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in AppData is not found in the empty JSON string", AppData.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!AppData.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `AppData` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // ensure the optional json data is an array if present
      if (jsonObj.get("extinfo") != null && !jsonObj.get("extinfo").isJsonNull() && !jsonObj.get("extinfo").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `extinfo` to be an array in the JSON string but got `%s`", jsonObj.get("extinfo").toString()));
      }
      if ((jsonObj.get("campaign_ids") != null && !jsonObj.get("campaign_ids").isJsonNull()) && !jsonObj.get("campaign_ids").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `campaign_ids` to be a primitive type in the JSON string but got `%s`", jsonObj.get("campaign_ids").toString()));
      }
      if ((jsonObj.get("install_referrer") != null && !jsonObj.get("install_referrer").isJsonNull()) && !jsonObj.get("install_referrer").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `install_referrer` to be a primitive type in the JSON string but got `%s`", jsonObj.get("install_referrer").toString()));
      }
      if ((jsonObj.get("installer_package") != null && !jsonObj.get("installer_package").isJsonNull()) && !jsonObj.get("installer_package").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `installer_package` to be a primitive type in the JSON string but got `%s`", jsonObj.get("installer_package").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("url_schemes") != null && !jsonObj.get("url_schemes").isJsonNull() && !jsonObj.get("url_schemes").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `url_schemes` to be an array in the JSON string but got `%s`", jsonObj.get("url_schemes").toString()));
      }
      if ((jsonObj.get("windows_attribution_id") != null && !jsonObj.get("windows_attribution_id").isJsonNull()) && !jsonObj.get("windows_attribution_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `windows_attribution_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("windows_attribution_id").toString()));
      }
      if ((jsonObj.get("app_id") != null && !jsonObj.get("app_id").isJsonNull()) && !jsonObj.get("app_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `app_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("app_id").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!AppData.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'AppData' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<AppData> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(AppData.class));

       return (TypeAdapter<T>) new TypeAdapter<AppData>() {
           @Override
           public void write(JsonWriter out, AppData value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public AppData read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of AppData given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of AppData
   * @throws IOException if the JSON string is invalid with respect to AppData
   */
  public static AppData fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, AppData.class);
  }

  /**
   * Convert an instance of AppData to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

