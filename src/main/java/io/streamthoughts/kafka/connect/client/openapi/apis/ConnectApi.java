/*
 * Copyright 2021 StreamThoughts.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.streamthoughts.kafka.connect.client.openapi.apis;

import com.google.gson.reflect.TypeToken;
import io.streamthoughts.kafka.connect.client.openapi.ApiCallback;
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.ApiResponse;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.Pair;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorPlugin;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorStatus;
import io.streamthoughts.kafka.connect.client.openapi.models.Root;
import io.streamthoughts.kafka.connect.client.openapi.models.TaskState;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectApi {
    private ApiClient localVarApiClient;

    public ConnectApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ConnectApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for deleteConnector
     *
     * @param name The name of the connector to delete (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 204 </td><td> No Content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call deleteConnectorCall(String name, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {};

        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "DELETE",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteConnectorValidateBeforeCall(String name, final ApiCallback _callback)
            throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling deleteConnector(Async)");
        }

        okhttp3.Call localVarCall = deleteConnectorCall(name, _callback);
        return localVarCall;
    }

    /**
     * deletes a connector, halting all tasks and deleting its configuration.
     *
     * @param name The name of the connector to delete (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 204 </td><td> No Content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public void deleteConnector(String name) throws ApiException {
        deleteConnectorWithHttpInfo(name);
    }

    /**
     * deletes a connector, halting all tasks and deleting its configuration.
     *
     * @param name The name of the connector to delete (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 204 </td><td> No Content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Void> deleteConnectorWithHttpInfo(String name) throws ApiException {
        okhttp3.Call localVarCall = deleteConnectorValidateBeforeCall(name, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * (asynchronously) deletes a connector, halting all tasks and deleting its configuration.
     *
     * @param name The name of the connector to delete (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 204 </td><td> No Content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call deleteConnectorAsync(String name, final ApiCallback<Void> _callback)
            throws ApiException {

        okhttp3.Call localVarCall = deleteConnectorValidateBeforeCall(name, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for getConnectVersion
     *
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> version of Kafka Connect </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectVersionCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "GET",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getConnectVersionValidateBeforeCall(final ApiCallback _callback)
            throws ApiException {

        okhttp3.Call localVarCall = getConnectVersionCall(_callback);
        return localVarCall;
    }

    /**
     * gets the version of the Connect worker that serves the REST request, the git commit ID of the
     * source code, and the Kafka cluster ID that the worker is connected to.
     *
     * @return Root
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> version of Kafka Connect </td><td>  -  </td></tr>
     * </table>
     */
    public Root getConnectVersion() throws ApiException {
        ApiResponse<Root> localVarResp = getConnectVersionWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * gets the version of the Connect worker that serves the REST request, the git commit ID of the
     * source code, and the Kafka cluster ID that the worker is connected to.
     *
     * @return ApiResponse&lt;Root&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> version of Kafka Connect </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Root> getConnectVersionWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = getConnectVersionValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<Root>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) gets the version of the Connect worker that serves the REST request, the git
     * commit ID of the source code, and the Kafka cluster ID that the worker is connected to.
     *
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> version of Kafka Connect </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectVersionAsync(final ApiCallback<Root> _callback)
            throws ApiException {

        okhttp3.Call localVarCall = getConnectVersionValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<Root>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getConnectorConfig
     *
     * @param name The name of the connector (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorConfigCall(String name, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/config"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "GET",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getConnectorConfigValidateBeforeCall(
            String name, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling getConnectorConfig(Async)");
        }

        okhttp3.Call localVarCall = getConnectorConfigCall(name, _callback);
        return localVarCall;
    }

    /**
     * gets the configuration for the connector.
     *
     * @param name The name of the connector (required)
     * @return Map&lt;String, String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public Map<String, String> getConnectorConfig(String name) throws ApiException {
        ApiResponse<Map<String, String>> localVarResp = getConnectorConfigWithHttpInfo(name);
        return localVarResp.getData();
    }

    /**
     * gets the configuration for the connector.
     *
     * @param name The name of the connector (required)
     * @return ApiResponse&lt;Map&lt;String, String&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Map<String, String>> getConnectorConfigWithHttpInfo(String name)
            throws ApiException {
        okhttp3.Call localVarCall = getConnectorConfigValidateBeforeCall(name, null);
        Type localVarReturnType = new TypeToken<Map<String, String>>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) gets the configuration for the connector.
     *
     * @param name The name of the connector (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorConfigAsync(
            String name, final ApiCallback<Map<String, String>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getConnectorConfigValidateBeforeCall(name, _callback);
        Type localVarReturnType = new TypeToken<Map<String, String>>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getConnectorInfo
     *
     * @param name The name of the created connector (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorInfoCall(String name, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "GET",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getConnectorInfoValidateBeforeCall(
            String name, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling getConnectorInfo(Async)");
        }

        okhttp3.Call localVarCall = getConnectorInfoCall(name, _callback);
        return localVarCall;
    }

    /**
     * gets information about the connector.
     *
     * @param name The name of the created connector (required)
     * @return ConnectorInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public ConnectorInfo getConnectorInfo(String name) throws ApiException {
        ApiResponse<ConnectorInfo> localVarResp = getConnectorInfoWithHttpInfo(name);
        return localVarResp.getData();
    }

    /**
     * gets information about the connector.
     *
     * @param name The name of the created connector (required)
     * @return ApiResponse&lt;ConnectorInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<ConnectorInfo> getConnectorInfoWithHttpInfo(String name)
            throws ApiException {
        okhttp3.Call localVarCall = getConnectorInfoValidateBeforeCall(name, null);
        Type localVarReturnType = new TypeToken<ConnectorInfo>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) gets information about the connector.
     *
     * @param name The name of the created connector (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorInfoAsync(
            String name, final ApiCallback<ConnectorInfo> _callback) throws ApiException {

        okhttp3.Call localVarCall = getConnectorInfoValidateBeforeCall(name, _callback);
        Type localVarReturnType = new TypeToken<ConnectorInfo>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getConnectorStatus
     *
     * @param name The name of the connector (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorStatusCall(String name, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/status"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "GET",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getConnectorStatusValidateBeforeCall(
            String name, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling getConnectorStatus(Async)");
        }

        okhttp3.Call localVarCall = getConnectorStatusCall(name, _callback);
        return localVarCall;
    }

    /**
     * gets current status of the connector, including whether it is running, failed or paused,
     * which worker it is assigned to, error information if it has failed, and the state of all its
     * tasks.
     *
     * @param name The name of the connector (required)
     * @return ConnectorStatus
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public ConnectorStatus getConnectorStatus(String name) throws ApiException {
        ApiResponse<ConnectorStatus> localVarResp = getConnectorStatusWithHttpInfo(name);
        return localVarResp.getData();
    }

    /**
     * gets current status of the connector, including whether it is running, failed or paused,
     * which worker it is assigned to, error information if it has failed, and the state of all its
     * tasks.
     *
     * @param name The name of the connector (required)
     * @return ApiResponse&lt;ConnectorStatus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<ConnectorStatus> getConnectorStatusWithHttpInfo(String name)
            throws ApiException {
        okhttp3.Call localVarCall = getConnectorStatusValidateBeforeCall(name, null);
        Type localVarReturnType = new TypeToken<ConnectorStatus>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) gets current status of the connector, including whether it is running,
     * failed or paused, which worker it is assigned to, error information if it has failed, and the
     * state of all its tasks.
     *
     * @param name The name of the connector (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorStatusAsync(
            String name, final ApiCallback<ConnectorStatus> _callback) throws ApiException {

        okhttp3.Call localVarCall = getConnectorStatusValidateBeforeCall(name, _callback);
        Type localVarReturnType = new TypeToken<ConnectorStatus>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getConnectorTaskStatus
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorTaskStatusCall(
            String name, Integer taskId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/tasks/{taskId}"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()))
                        .replaceAll(
                                "\\{" + "taskId" + "\\}",
                                localVarApiClient.escapeString(taskId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "GET",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getConnectorTaskStatusValidateBeforeCall(
            String name, Integer taskId, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling getConnectorTaskStatus(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException(
                    "Missing the required parameter 'taskId' when calling getConnectorTaskStatus(Async)");
        }

        okhttp3.Call localVarCall = getConnectorTaskStatusCall(name, taskId, _callback);
        return localVarCall;
    }

    /**
     * gets a task’s status.
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @return TaskState
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public TaskState getConnectorTaskStatus(String name, Integer taskId) throws ApiException {
        ApiResponse<TaskState> localVarResp = getConnectorTaskStatusWithHttpInfo(name, taskId);
        return localVarResp.getData();
    }

    /**
     * gets a task’s status.
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @return ApiResponse&lt;TaskState&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<TaskState> getConnectorTaskStatusWithHttpInfo(String name, Integer taskId)
            throws ApiException {
        okhttp3.Call localVarCall = getConnectorTaskStatusValidateBeforeCall(name, taskId, null);
        Type localVarReturnType = new TypeToken<TaskState>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) gets a task’s status.
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getConnectorTaskStatusAsync(
            String name, Integer taskId, final ApiCallback<TaskState> _callback)
            throws ApiException {

        okhttp3.Call localVarCall =
                getConnectorTaskStatusValidateBeforeCall(name, taskId, _callback);
        Type localVarReturnType = new TypeToken<TaskState>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listConnectorPlugins
     *
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call listConnectorPluginsCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/connector-plugins";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "GET",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call listConnectorPluginsValidateBeforeCall(final ApiCallback _callback)
            throws ApiException {

        okhttp3.Call localVarCall = listConnectorPluginsCall(_callback);
        return localVarCall;
    }

    /**
     * get the list of connector plugins installed in the Kafka Connect cluster.
     *
     * @return List&lt;ConnectorPlugin&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public List<ConnectorPlugin> listConnectorPlugins() throws ApiException {
        ApiResponse<List<ConnectorPlugin>> localVarResp = listConnectorPluginsWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * get the list of connector plugins installed in the Kafka Connect cluster.
     *
     * @return ApiResponse&lt;List&lt;ConnectorPlugin&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<List<ConnectorPlugin>> listConnectorPluginsWithHttpInfo()
            throws ApiException {
        okhttp3.Call localVarCall = listConnectorPluginsValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<List<ConnectorPlugin>>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) get the list of connector plugins installed in the Kafka Connect cluster.
     *
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call listConnectorPluginsAsync(
            final ApiCallback<List<ConnectorPlugin>> _callback) throws ApiException {

        okhttp3.Call localVarCall = listConnectorPluginsValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<List<ConnectorPlugin>>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for listConnectors
     *
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call listConnectorsCall(final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/connectors";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "GET",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call listConnectorsValidateBeforeCall(final ApiCallback _callback)
            throws ApiException {

        okhttp3.Call localVarCall = listConnectorsCall(_callback);
        return localVarCall;
    }

    /**
     * gets the list of active connectors
     *
     * @return List&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public List<String> listConnectors() throws ApiException {
        ApiResponse<List<String>> localVarResp = listConnectorsWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * gets the list of active connectors
     *
     * @return ApiResponse&lt;List&lt;String&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<List<String>> listConnectorsWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = listConnectorsValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<List<String>>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) gets the list of active connectors
     *
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call listConnectorsAsync(final ApiCallback<List<String>> _callback)
            throws ApiException {

        okhttp3.Call localVarCall = listConnectorsValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<List<String>>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for pauseConnector
     *
     * @param name The name of the connector to pause (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call pauseConnectorCall(String name, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/pause"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {};

        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "PUT",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call pauseConnectorValidateBeforeCall(String name, final ApiCallback _callback)
            throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling pauseConnector(Async)");
        }

        okhttp3.Call localVarCall = pauseConnectorCall(name, _callback);
        return localVarCall;
    }

    /**
     * pauses the connector and its tasks, which stops message processing until the connector is
     * resumed. This call is asynchronous and the tasks will not transition to PAUSED state at the
     * same time.
     *
     * @param name The name of the connector to pause (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public void pauseConnector(String name) throws ApiException {
        pauseConnectorWithHttpInfo(name);
    }

    /**
     * pauses the connector and its tasks, which stops message processing until the connector is
     * resumed. This call is asynchronous and the tasks will not transition to PAUSED state at the
     * same time.
     *
     * @param name The name of the connector to pause (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Void> pauseConnectorWithHttpInfo(String name) throws ApiException {
        okhttp3.Call localVarCall = pauseConnectorValidateBeforeCall(name, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * (asynchronously) pauses the connector and its tasks, which stops message processing until the
     * connector is resumed. This call is asynchronous and the tasks will not transition to PAUSED
     * state at the same time.
     *
     * @param name The name of the connector to pause (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call pauseConnectorAsync(String name, final ApiCallback<Void> _callback)
            throws ApiException {

        okhttp3.Call localVarCall = pauseConnectorValidateBeforeCall(name, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for restartConnector
     *
     * @param name Name of the created connector (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call restartConnectorCall(String name, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/restart"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {};

        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "POST",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call restartConnectorValidateBeforeCall(
            String name, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling restartConnector(Async)");
        }

        okhttp3.Call localVarCall = restartConnectorCall(name, _callback);
        return localVarCall;
    }

    /**
     * restarts the connector.
     *
     * @param name Name of the created connector (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public void restartConnector(String name) throws ApiException {
        restartConnectorWithHttpInfo(name);
    }

    /**
     * restarts the connector.
     *
     * @param name Name of the created connector (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Void> restartConnectorWithHttpInfo(String name) throws ApiException {
        okhttp3.Call localVarCall = restartConnectorValidateBeforeCall(name, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * (asynchronously) restarts the connector.
     *
     * @param name Name of the created connector (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call restartConnectorAsync(String name, final ApiCallback<Void> _callback)
            throws ApiException {

        okhttp3.Call localVarCall = restartConnectorValidateBeforeCall(name, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for restartConnectorTask
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call restartConnectorTaskCall(
            String name, Integer taskId, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/tasks/{taskId}/restart"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()))
                        .replaceAll(
                                "\\{" + "taskId" + "\\}",
                                localVarApiClient.escapeString(taskId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {};

        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "PUT",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call restartConnectorTaskValidateBeforeCall(
            String name, Integer taskId, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling restartConnectorTask(Async)");
        }

        // verify the required parameter 'taskId' is set
        if (taskId == null) {
            throw new ApiException(
                    "Missing the required parameter 'taskId' when calling restartConnectorTask(Async)");
        }

        okhttp3.Call localVarCall = restartConnectorTaskCall(name, taskId, _callback);
        return localVarCall;
    }

    /**
     * restarts an individual task.
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public void restartConnectorTask(String name, Integer taskId) throws ApiException {
        restartConnectorTaskWithHttpInfo(name, taskId);
    }

    /**
     * restarts an individual task.
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Void> restartConnectorTaskWithHttpInfo(String name, Integer taskId)
            throws ApiException {
        okhttp3.Call localVarCall = restartConnectorTaskValidateBeforeCall(name, taskId, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * (asynchronously) restarts an individual task.
     *
     * @param name The name of the connector?&#x3D;. (required)
     * @param taskId The ID of the task to restart. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call restartConnectorTaskAsync(
            String name, Integer taskId, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = restartConnectorTaskValidateBeforeCall(name, taskId, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for resumeConnector
     *
     * @param name The name of the connector to resume (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call resumeConnectorCall(String name, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/resume"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {};

        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "PUT",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call resumeConnectorValidateBeforeCall(String name, final ApiCallback _callback)
            throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling resumeConnector(Async)");
        }

        okhttp3.Call localVarCall = resumeConnectorCall(name, _callback);
        return localVarCall;
    }

    /**
     * resumes a paused connector or do nothing if the connector is not paused. This call is
     * asynchronous and the tasks will not transition to RUNNING state at the same time.
     *
     * @param name The name of the connector to resume (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public void resumeConnector(String name) throws ApiException {
        resumeConnectorWithHttpInfo(name);
    }

    /**
     * resumes a paused connector or do nothing if the connector is not paused. This call is
     * asynchronous and the tasks will not transition to RUNNING state at the same time.
     *
     * @param name The name of the connector to resume (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Void> resumeConnectorWithHttpInfo(String name) throws ApiException {
        okhttp3.Call localVarCall = resumeConnectorValidateBeforeCall(name, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * (asynchronously) resumes a paused connector or do nothing if the connector is not paused.
     * This call is asynchronous and the tasks will not transition to RUNNING state at the same
     * time.
     *
     * @param name The name of the connector to resume (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 202 </td><td> Accepted </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Unauthorized </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> (Conflict) a rebalance is in progress. </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call resumeConnectorAsync(String name, final ApiCallback<Void> _callback)
            throws ApiException {

        okhttp3.Call localVarCall = resumeConnectorValidateBeforeCall(name, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }
    /**
     * Build call for updateOrCreateConnectorConfig
     *
     * @param name The name of the connector (required)
     * @param requestBody (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call updateOrCreateConnectorConfigCall(
            String name, Map<String, String> requestBody, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = requestBody;

        // create path and map variables
        String localVarPath =
                "/connectors/{name}/config"
                        .replaceAll(
                                "\\{" + "name" + "\\}",
                                localVarApiClient.escapeString(name.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {"application/json"};
        final String localVarContentType =
                localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(
                localVarPath,
                "PUT",
                localVarQueryParams,
                localVarCollectionQueryParams,
                localVarPostBody,
                localVarHeaderParams,
                localVarCookieParams,
                localVarFormParams,
                localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call updateOrCreateConnectorConfigValidateBeforeCall(
            String name, Map<String, String> requestBody, final ApiCallback _callback)
            throws ApiException {

        // verify the required parameter 'name' is set
        if (name == null) {
            throw new ApiException(
                    "Missing the required parameter 'name' when calling updateOrCreateConnectorConfig(Async)");
        }

        // verify the required parameter 'requestBody' is set
        if (requestBody == null) {
            throw new ApiException(
                    "Missing the required parameter 'requestBody' when calling updateOrCreateConnectorConfig(Async)");
        }

        okhttp3.Call localVarCall = updateOrCreateConnectorConfigCall(name, requestBody, _callback);
        return localVarCall;
    }

    /**
     * creates a new connector using the given configuration, or updates the configuration for an
     * existing connector.
     *
     * @param name The name of the connector (required)
     * @param requestBody (required)
     * @return ConnectorInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public ConnectorInfo updateOrCreateConnectorConfig(String name, Map<String, String> requestBody)
            throws ApiException {
        ApiResponse<ConnectorInfo> localVarResp =
                updateOrCreateConnectorConfigWithHttpInfo(name, requestBody);
        return localVarResp.getData();
    }

    /**
     * creates a new connector using the given configuration, or updates the configuration for an
     * existing connector.
     *
     * @param name The name of the connector (required)
     * @param requestBody (required)
     * @return ApiResponse&lt;ConnectorInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     *     response body
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<ConnectorInfo> updateOrCreateConnectorConfigWithHttpInfo(
            String name, Map<String, String> requestBody) throws ApiException {
        okhttp3.Call localVarCall =
                updateOrCreateConnectorConfigValidateBeforeCall(name, requestBody, null);
        Type localVarReturnType = new TypeToken<ConnectorInfo>() {}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * (asynchronously) creates a new connector using the given configuration, or updates the
     * configuration for an existing connector.
     *
     * @param name The name of the connector (required)
     * @param requestBody (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     *     object
     * @http.response.details
     *     <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call updateOrCreateConnectorConfigAsync(
            String name,
            Map<String, String> requestBody,
            final ApiCallback<ConnectorInfo> _callback)
            throws ApiException {

        okhttp3.Call localVarCall =
                updateOrCreateConnectorConfigValidateBeforeCall(name, requestBody, _callback);
        Type localVarReturnType = new TypeToken<ConnectorInfo>() {}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
