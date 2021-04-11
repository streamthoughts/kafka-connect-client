# ConnectApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteConnector**](ConnectApi.md#deleteConnector) | **DELETE** /connectors/{name} | 
[**getConnectVersion**](ConnectApi.md#getConnectVersion) | **GET** / | 
[**getConnectorConfig**](ConnectApi.md#getConnectorConfig) | **GET** /connectors/{name}/config | 
[**getConnectorInfo**](ConnectApi.md#getConnectorInfo) | **GET** /connectors/{name} | 
[**getConnectorStateInfo**](ConnectApi.md#getConnectorStateInfo) | **GET** /connectors/{name}/status | 
[**getConnectorTaskInfos**](ConnectApi.md#getConnectorTaskInfos) | **GET** /connectors/{name}/tasks | 
[**getConnectorTaskStatus**](ConnectApi.md#getConnectorTaskStatus) | **GET** /connectors/{name}/tasks/{taskId}/status | 
[**listConnectorPlugins**](ConnectApi.md#listConnectorPlugins) | **GET** /connector-plugins | 
[**listConnectors**](ConnectApi.md#listConnectors) | **GET** /connectors | 
[**pauseConnector**](ConnectApi.md#pauseConnector) | **PUT** /connectors/{name}/pause | 
[**restartConnector**](ConnectApi.md#restartConnector) | **POST** /connectors/{name}/restart | 
[**restartConnectorTask**](ConnectApi.md#restartConnectorTask) | **PUT** /connectors/{name}/tasks/{taskId}/restart | 
[**resumeConnector**](ConnectApi.md#resumeConnector) | **PUT** /connectors/{name}/resume | 
[**updateOrCreateConnectorConfig**](ConnectApi.md#updateOrCreateConnectorConfig) | **PUT** /connectors/{name}/config | 


<a name="deleteConnector"></a>
# **deleteConnector**
> deleteConnector(name)



deletes a connector, halting all tasks and deleting its configuration.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector to delete
    try {
      apiInstance.deleteConnector(name);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#deleteConnector");
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
 **name** | **String**| The name of the connector to delete |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | No Content |  -  |
**409** | (Conflict) a rebalance is in progress. |  -  |

<a name="getConnectVersion"></a>
# **getConnectVersion**
> Version getConnectVersion()



gets the version of the Connect worker that serves the REST request, the git commit ID of the source code, and the Kafka cluster ID that the worker is connected to.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    try {
      Version result = apiInstance.getConnectVersion();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#getConnectVersion");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**Version**](Version.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | version of Kafka Connect |  -  |

<a name="getConnectorConfig"></a>
# **getConnectorConfig**
> Map&lt;String, String&gt; getConnectorConfig(name)



gets the configuration for the connector.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector
    try {
      Map<String, String> result = apiInstance.getConnectorConfig(name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#getConnectorConfig");
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
 **name** | **String**| The name of the connector |

### Return type

**Map&lt;String, String&gt;**

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

<a name="getConnectorInfo"></a>
# **getConnectorInfo**
> ConnectorInfo getConnectorInfo(name)



gets information about the connector.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the created connector
    try {
      ConnectorInfo result = apiInstance.getConnectorInfo(name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#getConnectorInfo");
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
 **name** | **String**| The name of the created connector |

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

<a name="getConnectorStateInfo"></a>
# **getConnectorStateInfo**
> ConnectorStateInfo getConnectorStateInfo(name)



gets current status of the connector, including whether it is running, failed or paused, which worker it is assigned to, error information if it has failed, and the state of all its tasks.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector
    try {
      ConnectorStateInfo result = apiInstance.getConnectorStateInfo(name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#getConnectorStateInfo");
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
 **name** | **String**| The name of the connector |

### Return type

[**ConnectorStateInfo**](ConnectorStateInfo.md)

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

<a name="getConnectorTaskInfos"></a>
# **getConnectorTaskInfos**
> List&lt;TaskInfo&gt; getConnectorTaskInfos(name)



gets a task’s status.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector.
    try {
      List<TaskInfo> result = apiInstance.getConnectorTaskInfos(name);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#getConnectorTaskInfos");
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
 **name** | **String**| The name of the connector. |

### Return type

[**List&lt;TaskInfo&gt;**](TaskInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="getConnectorTaskStatus"></a>
# **getConnectorTaskStatus**
> TaskState getConnectorTaskStatus(name, taskId)



gets a task&#39;s status.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector.
    Integer taskId = 56; // Integer | The ID of the task to restart.
    try {
      TaskState result = apiInstance.getConnectorTaskStatus(name, taskId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#getConnectorTaskStatus");
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
 **name** | **String**| The name of the connector. |
 **taskId** | **Integer**| The ID of the task to restart. |

### Return type

[**TaskState**](TaskState.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="listConnectorPlugins"></a>
# **listConnectorPlugins**
> List&lt;ConnectorPlugin&gt; listConnectorPlugins()



get the list of connector plugins installed in the Kafka Connect cluster.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    try {
      List<ConnectorPlugin> result = apiInstance.listConnectorPlugins();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#listConnectorPlugins");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ConnectorPlugin&gt;**](ConnectorPlugin.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="listConnectors"></a>
# **listConnectors**
> List&lt;String&gt; listConnectors()



gets the list of active connectors

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    try {
      List<String> result = apiInstance.listConnectors();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#listConnectors");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**List&lt;String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="pauseConnector"></a>
# **pauseConnector**
> pauseConnector(name)



pauses the connector and its tasks, which stops message processing until the connector is resumed. This call is asynchronous and the tasks will not transition to PAUSED state at the same time.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector to pause
    try {
      apiInstance.pauseConnector(name);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#pauseConnector");
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
 **name** | **String**| The name of the connector to pause |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**202** | Accepted |  -  |
**401** | Unauthorized |  -  |
**409** | (Conflict) a rebalance is in progress. |  -  |

<a name="restartConnector"></a>
# **restartConnector**
> restartConnector(name)



restarts the connector.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | Name of the created connector
    try {
      apiInstance.restartConnector(name);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#restartConnector");
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**401** | Unauthorized |  -  |
**409** | (Conflict) a rebalance is in progress. |  -  |

<a name="restartConnectorTask"></a>
# **restartConnectorTask**
> restartConnectorTask(name, taskId)



restarts an individual task.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector?=.
    Integer taskId = 56; // Integer | The ID of the task to restart.
    try {
      apiInstance.restartConnectorTask(name, taskId);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#restartConnectorTask");
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
 **name** | **String**| The name of the connector?&#x3D;. |
 **taskId** | **Integer**| The ID of the task to restart. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**202** | Accepted |  -  |
**401** | Unauthorized |  -  |

<a name="resumeConnector"></a>
# **resumeConnector**
> resumeConnector(name)



resumes a paused connector or do nothing if the connector is not paused. This call is asynchronous and the tasks will not transition to RUNNING state at the same time.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector to resume
    try {
      apiInstance.resumeConnector(name);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#resumeConnector");
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
 **name** | **String**| The name of the connector to resume |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**202** | Accepted |  -  |
**401** | Unauthorized |  -  |
**409** | (Conflict) a rebalance is in progress. |  -  |

<a name="updateOrCreateConnectorConfig"></a>
# **updateOrCreateConnectorConfig**
> ConnectorInfo updateOrCreateConnectorConfig(name, requestBody)



creates a new connector using the given configuration, or updates the configuration for an existing connector.

### Example
```java
// Import classes:
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.*;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    ConnectApi apiInstance = new ConnectApi(defaultClient);
    String name = "name_example"; // String | The name of the connector
    Map<String, String> requestBody = new HashMap(); // Map<String, String> | 
    try {
      ConnectorInfo result = apiInstance.updateOrCreateConnectorConfig(name, requestBody);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ConnectApi#updateOrCreateConnectorConfig");
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
 **name** | **String**| The name of the connector |
 **requestBody** | [**Map&lt;String, String&gt;**](String.md)|  |

### Return type

[**ConnectorInfo**](ConnectorInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

