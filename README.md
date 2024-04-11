# capi-v3-java

Snap Conversions API V3
- API version: 1.0.0
  - Generator version: 7.4.0

No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)


*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven (3.8.3+)/Gradle (7.2+)

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.snap.business.sdk.v3</groupId>
  <artifactId>capi-v3-java</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()     // Needed if the 'snap-capi' jar has been published to maven central.
    mavenLocal()       // Needed if the 'snap-capi' jar has been published to the local maven repo.
  }

  dependencies {
     implementation "com.snap.business.sdk.v3:snap-capi:1.0.0"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/snap-capi-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:
import com.snap.business.sdk.v3.ApiClient;
import com.snap.business.sdk.v3.ApiException;
import com.snap.business.sdk.v3.Configuration;
import com.snap.business.sdk.v3.auth.*;
import com.snap.business.sdk.v3.models.*;
import com.snap.business.sdk.v3.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://tr.snapchat.com");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String assetId = "assetId_example"; // String | The asset ID for which log data is being requested.
    try {
      GetLogsResponse result = apiInstance.getValidationLogs(assetId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#getValidationLogs");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://tr.snapchat.com*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**getValidationLogs**](docs/DefaultApi.md#getValidationLogs) | **GET** /v3/{asset_id}/events/validate/logs | 
*DefaultApi* | [**getValidationStats**](docs/DefaultApi.md#getValidationStats) | **GET** /v3/{asset_id}/events/validate/stats | 
*DefaultApi* | [**sendEvent**](docs/DefaultApi.md#sendEvent) | **POST** /v3/{asset_id}/events | 
*DefaultApi* | [**sendValidationEvent**](docs/DefaultApi.md#sendValidationEvent) | **POST** /v3/{asset_id}/events/validate | 


## Documentation for Models

 - [AppData](docs/AppData.md)
 - [Content](docs/Content.md)
 - [ConversionRequestV3](docs/ConversionRequestV3.md)
 - [CustomData](docs/CustomData.md)
 - [ErrorDetails](docs/ErrorDetails.md)
 - [EventLogs](docs/EventLogs.md)
 - [EventMetadata](docs/EventMetadata.md)
 - [EventResponse](docs/EventResponse.md)
 - [GetLogsResponse](docs/GetLogsResponse.md)
 - [GetStatsResponse](docs/GetStatsResponse.md)
 - [LogsDetails](docs/LogsDetails.md)
 - [SendEventRequest](docs/SendEventRequest.md)
 - [StatsData](docs/StatsData.md)
 - [StatsResponse](docs/StatsResponse.md)
 - [UserData](docs/UserData.md)
 - [ValidateEventLogV3](docs/ValidateEventLogV3.md)
 - [ValidateResponse](docs/ValidateResponse.md)


<a id="documentation-for-authorization"></a>
## Documentation for Authorization


Authentication schemes defined for the API:
<a id="bearerAuth"></a>
### bearerAuth

- **Type**: HTTP Bearer Token authentication (JWT)


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



