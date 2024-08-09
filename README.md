# capi-v3-java

Snap Conversions API V3
- API version: 1.1.5


Business SDK V3
The Business SDK V3 simplifies the setup and integration process for partners utilizing Snapchat's Conversion API.

- Easy Authentication: Streamlined access to Snapchat services.
- Simplified API Interactions: Intuitive commands and functions to communicate with the API.

Our goal is to significantly reduce the time and effort required for sending events, making your development process smoother and more efficient.


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
  <artifactId>snap-capi</artifactId>
  <version>1.1.5</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
  repositories {
    mavenCentral()
  }

  dependencies {
     implementation "com.snap.business.sdk.v3:snap-capi:1.1.5"
  }
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/snap-capi-1.1.5.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

### Usage Example

For more examples check the [examples](src/main/java/com/snap/business/sdk/v3/examples) folder.
```java
// Import classes:
import com.snap.business.sdk.v3.api.ConversionApiClient;
import com.snap.business.sdk.v3.model.CapiEvent;
import com.snap.business.sdk.v3.util.ServerEnv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example {
  public static void main(String[] args) {
      ConversionApiClient capiClient = 
              new ConversionApiClient.Builder()
                      .setAccessToken("<capi_access_token>") // Required
                      .setServerEnv(SERVER_ENV.PROD) // Or SERVER_ENV.Staging Required
                      .setOkHttpClient(okHttpClient) // Optional
                      .withDebuggingEnabled() // Optional
                      .withInternalDebuggingEnabled() // Optional
                      .build();

      CapiEvent capiEvent = new CapiEvent();
      capiEvent.setEventName("purchase");
      capiEvent.setEventTime(1712009009L);
      capiEvent.setActionSource("web");
      capiEvent.dataProcessingOptions(Collections.singletonList("LDU"));

      UserData userData = new UserData();
      userData.setEm(
              Collections.singletonList(
                      "7b17fb0bd173f625b58636fb796407c22b3d16fc78302d79f0fd30c2fc2fc068"));
      capiEvent.setUserData(userData);

      CustomData customData = new CustomData();
      customData.setCurrency("USD");
      customData.setValue(BigDecimal.valueOf(142.52));
      capiEvent.setCustomData(customData);
    
      // Send Event Sync
      EventResponse apiResponse = capiClient.sendEvent(testAssetId, capiPixelEvent);
      // Send Batch Events Sync
      EventResponse apiResponseBatch = capiClient.sendEventsBatch(testAssetId, capiBatchEvents);
      
      // Send Event Async
      capiClient.sendEventAsync(testAssetId, capiPixelEvent);
      
      // Send Batch Events Async
      capiClient.sendEventsBatchAsync(testAssetId, capiBatchEvents);
  }
}

```




