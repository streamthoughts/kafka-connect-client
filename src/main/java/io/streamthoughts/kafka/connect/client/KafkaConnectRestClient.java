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
package io.streamthoughts.kafka.connect.client;

import io.streamthoughts.kafka.connect.client.openapi.ApiCallback;
import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorPlugin;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorStateInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.State;
import io.streamthoughts.kafka.connect.client.openapi.models.TaskInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.TaskState;
import io.streamthoughts.kafka.connect.client.openapi.models.Version;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@code KafkaConnectRestClient} can be used for building a new {@link ConnectApi} for Kafka
 * Connect.
 */
public class KafkaConnectRestClient {

  private static final Logger LOG = LoggerFactory.getLogger(KafkaConnectRestClient.class);

  private final ConnectApi api;

  /**
   * Creates a new {@link KafkaConnectRestClient} instance using default ApiClient.
   *
   * @see io.streamthoughts.kafka.connect.client.openapi.Configuration#getDefaultApiClient().
   */
  public KafkaConnectRestClient() {
    this.api = new ConnectApi();
  }

  /**
   * Creates a new {@link KafkaConnectRestClient} instance using the specified {@link ApiClient}.
   *
   * @param apiClient the {@link ApiClient}.
   */
  public KafkaConnectRestClient(final ApiClient apiClient) {
    this(new ConnectApi(Objects.requireNonNull(apiClient, "apiClient should not be null")));
  }

  @TestOnly
  KafkaConnectRestClient(final ConnectApi api) {
    this.api = Objects.requireNonNull(api, "api should not be nul");
  }

  /** @return the {@link ConnectApi} instance. */
  public ConnectApi api() {
    return api;
  }

  /** @see ConnectApi#getConnectVersion(). */
  public CompletableFuture<Version> getConnectVersion() {
    return execute(api::getConnectVersionAsync);
  }

  /** @see ConnectApi#listConnectorPlugins(). */
  public CompletableFuture<List<ConnectorPlugin>> listConnectorPlugins() {
    return execute(api::listConnectorPluginsAsync);
  }

  /** @see ConnectApi#listConnectors(). */
  public CompletableFuture<List<String>> listConnectors() {
    return execute(api::listConnectorsAsync);
  }

  /** @see ConnectApi#getConnectorConfig(String). */
  public CompletableFuture<Map<String, String>> getConnectorConfig(final String connectorName) {
    return execute((callback) -> api.getConnectorConfigAsync(connectorName, callback));
  }

  /** @see ConnectApi#getConnectorInfo(String). */
  public CompletableFuture<ConnectorInfo> getConnectorInfo(final String connectorName) {
    return execute((callback) -> api.getConnectorInfoAsync(connectorName, callback));
  }

  /** @see ConnectApi#getConnectorStateInfo(String). */
  public CompletableFuture<ConnectorStateInfo> getConnectorStateInfo(final String connectorName) {
    return execute((callback) -> api.getConnectorStateInfoAsync(connectorName, callback));
  }

  /** @see ConnectApi#getConnectorTaskInfos(String). */
  public CompletableFuture<List<TaskInfo>> getConnectorTaskInfos(final String connectorName) {
    return execute((callback) -> api.getConnectorTaskInfosAsync(connectorName, callback));
  }

  /** @see ConnectApi#deleteConnector(String). */
  public CompletableFuture<Void> deleteConnector(final String connectorName) {
    return execute((callback) -> api.deleteConnectorAsync(connectorName, callback));
  }

  /** @see ConnectApi#pauseConnector(String). */
  public CompletableFuture<Void> pauseConnector(final String connectorName) {
    return execute((callback) -> api.pauseConnectorAsync(connectorName, callback));
  }

  /** @see ConnectApi#resumeConnector(String). */
  public CompletableFuture<Void> resumeConnector(final String connectorName) {
    return execute((callback) -> api.resumeConnectorAsync(connectorName, callback));
  }

  /** @see ConnectApi#updateOrCreateConnectorConfig(String, Map). */
  public CompletableFuture<ConnectorInfo> updateOrCreateConnectorConfig(
      final String connectorName, final Map<String, String> config) {
    return execute(
        (callback) -> api.updateOrCreateConnectorConfigAsync(connectorName, config, callback));
  }

  /** @see ConnectApi#updateOrCreateConnectorConfig(String, Map). */
  public CompletableFuture<ConnectorInfo> updateOrCreateConnectorConfig(
      final String connectorName, final ConnectorConfig config) {
    return execute(
        (callback) ->
            api.updateOrCreateConnectorConfigAsync(connectorName, config.build(), callback));
  }

  /** @see ConnectApi#getConnectorTaskStatus(String, Integer). */
  public CompletableFuture<TaskState> getConnectorTaskStatus(
      final String connectorName, int taskId) {
    return execute((callback) -> api.getConnectorTaskStatusAsync(connectorName, taskId, callback));
  }

  /** @see ConnectApi#restartConnectorTask(String, Integer). */
  public CompletableFuture<Void> restartConnectorTask(final String connectorName, int taskId) {
    return execute((callback) -> api.restartConnectorTaskAsync(connectorName, taskId, callback));
  }

  /**
   * Helper method to list all Tasks wmatching the given {@link State} predicate.
   *
   * @return a {@link CompletableFuture} of the list of {@link TaskState}.
   */
  public CompletableFuture<List<TaskState>> listTasksWithState(final Predicate<State> predicate) {
    Objects.requireNonNull(predicate, "predicate should not be null");
    final List<String> connectors = api.listConnectors();
    return allOf(
            connectors.parallelStream()
                .map(
                    connector ->
                        getConnectorStateInfo(connector)
                            .thenApply(task -> filterTasks(task, predicate)))
                .collect(Collectors.toList()))
        .thenApply(it -> it.stream().flatMap(List::stream).collect(Collectors.toList()));
  }

  @NotNull
  private List<TaskState> filterTasks(
      final ConnectorStateInfo connectorStateInfo, final Predicate<State> predicate) {
    return connectorStateInfo.getTasks().stream()
        .filter(task -> predicate.test(task.getState()))
        .collect(Collectors.toList());
  }

  /**
   * Helper method to list all Connectors matching the given {@link State} predicate.
   *
   * @return a {@link CompletableFuture} of the list of {@link ConnectorStateInfo}.
   */
  public CompletableFuture<List<ConnectorStateInfo>> listConnectorsWithState(
      final Predicate<State> predicate) {
    Objects.requireNonNull(predicate, "predicate should not be null");
    final CompletableFuture<List<ConnectorStateInfo>> allOf =
        allOf(
            api.listConnectors().parallelStream()
                .map(this::getConnectorStateInfo)
                .collect(Collectors.toList()));

    return allOf.thenApply(
        it ->
            it.stream()
                .filter(state -> predicate.test(state.getConnector().getState()))
                .collect(Collectors.toList()));
  }

  private static <T> CompletableFuture<T> execute(final Function<ApiCallback<T>, ?> function) {
    final CompletableFuture<T> future = new CompletableFuture<>();
    try {
      function.apply(getCallback(future));
    } catch (final ApiException e) {
      future.completeExceptionally(e);
    }
    return future;
  }

  @NotNull
  private static <T> ApiCallback<T> getCallback(final CompletableFuture<T> future) {
    return new ApiCallback<>() {
      @Override
      public void onFailure(
          final ApiException e,
          final int statusCode,
          final Map<String, List<String>> responseHeaders) {
        // The client has failed to send the request
        if (statusCode == 0) {
          LOG.error("Failed to execute HTTP Client ", e);
        } else {
          LOG.error(
              "Unexpected response from from remote server (statusCode:'" + statusCode + "')");
          LOG.error("Response: {}", e.getResponseBody());
        }
        future.completeExceptionally(e);
      }

      @Override
      public void onSuccess(
          final T result, final int statusCode, final Map<String, List<String>> responseHeaders) {
        future.complete(result);
      }

      @Override
      public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {}

      @Override
      public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {}
    };
  }

  private static <T> CompletableFuture<List<T>> allOf(
      final List<CompletableFuture<T>> futuresList) {
    CompletableFuture<Void> allFuturesResult =
        CompletableFuture.allOf(futuresList.toArray(new CompletableFuture[0]));
    return allFuturesResult.thenApply(
        v -> futuresList.stream().map(CompletableFuture::join).collect(Collectors.<T>toList()));
  }
}
