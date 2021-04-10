# AzkarraV1Api

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getConnector**](AzkarraV1Api.md#getConnector) | **GET** /connectors/{name} | 


<a name="getConnector"></a>
# **getConnector**
> ConnectorInfo getConnector(name)



gets information about the connector.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.AzkarraV1Api;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    AzkarraV1Api apiInstance = new AzkarraV1Api(defaultClient);
    String name = "name_example"; // String | Name of the created connector
    try {
      ConnectorInfo result = apiInstance.getConnector(name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling AzkarraV1Api#getConnector");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| Name of the created connector |

### Return type

[**ConnectorInfo**](ConnectorInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |

