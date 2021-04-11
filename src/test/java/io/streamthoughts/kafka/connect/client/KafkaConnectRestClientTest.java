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

import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;

import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorStateInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.State;
import io.streamthoughts.kafka.connect.client.openapi.models.TaskState;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.junit.jupiter.MockServerExtension;

@ExtendWith(MockServerExtension.class)
public class KafkaConnectRestClientTest {

  public static final String TEST_CONNECTOR = "test-connector";
  public static final Predicate<State> STATE_PREDICATE = state -> state.equals(State.FAILED);

  private KafkaConnectRestClient client;

  private final ClientAndServer server;

  public KafkaConnectRestClientTest(final ClientAndServer server) {
    this.server = server;
  }

  @BeforeEach
  public void setUp() {
    server
        .when(request().withMethod("GET").withPath("/connectors"), exactly(1))
        .respond(
            response()
                .withStatusCode(200)
                .withHeader("Content-type", "application/json")
                .withBody(exact("['" + TEST_CONNECTOR + "']"))
                .withDelay(TimeUnit.SECONDS, 1));

    ApiClient client = Config.fromUrl("http://localhost:" + server.getLocalPort());
    Configuration.setDefaultApiClient(client);
    this.client = new KafkaConnectRestClient();
  }

  @Test
  public void should_return_no_failed_task_when_tasks_running() throws Exception {
    addServerResponseForConnector(
        TEST_CONNECTOR, getConnectorStateInfoJSON(TEST_CONNECTOR, "RUNNING", "RUNNING"));
    final List<TaskState> tasks = client.listTasksWithState(STATE_PREDICATE).get();
    Assertions.assertNotNull(tasks);
    Assertions.assertTrue(tasks.isEmpty());
  }

  @Test
  public void should_return_all_failed_tasks_when_tasks_failed() throws Exception {
    addServerResponseForConnector(
        TEST_CONNECTOR, getConnectorStateInfoJSON(TEST_CONNECTOR, "RUNNING", "FAILED"));
    final List<TaskState> tasks = client.listTasksWithState(STATE_PREDICATE).get();
    Assertions.assertNotNull(tasks);
    Assertions.assertFalse(tasks.isEmpty());
    final TaskState taskState = tasks.get(0);
    Assertions.assertEquals(0, taskState.getId());
    Assertions.assertEquals(State.FAILED, taskState.getState());
  }

  @Test
  public void should_return_no_failed_connector_when_connectors_running() throws Exception {
    addServerResponseForConnector(
        TEST_CONNECTOR, getConnectorStateInfoJSON(TEST_CONNECTOR, "RUNNING", "RUNNING"));
    final List<ConnectorStateInfo> connectors =
        client.listConnectorsWithState(STATE_PREDICATE).get();
    Assertions.assertNotNull(connectors);
    Assertions.assertTrue(connectors.isEmpty());
  }

  @Test
  public void should_return_all_failed_connectors_when_connectors_failed() throws Exception {
    addServerResponseForConnector(
        TEST_CONNECTOR, getConnectorStateInfoJSON(TEST_CONNECTOR, "FAILED", "RUNNING"));
    final List<ConnectorStateInfo> connectors =
        client.listConnectorsWithState(STATE_PREDICATE).get();
    Assertions.assertNotNull(connectors);
    Assertions.assertFalse(connectors.isEmpty());
    final ConnectorStateInfo connectorStateInfo = connectors.get(0);
    Assertions.assertEquals(TEST_CONNECTOR, connectorStateInfo.getName());
  }

  private void addServerResponseForConnector(final String connectorName, final String body) {
    server
        .when(
            request().withMethod("GET").withPath("/connectors/" + connectorName + "/status"),
            exactly(1))
        .respond(
            response()
                .withStatusCode(200)
                .withHeader("Content-type", "application/json")
                .withBody(exact(body))
                .withDelay(TimeUnit.SECONDS, 1));
  }

  static String getConnectorStateInfoJSON(
      final String connectorName, final String connectorState, final String taskState) {
    return " {"
        + " name: '"
        + connectorName
        + "',"
        + " connector: { state: '"
        + connectorState
        + "', worker_id: 'cp-kafka-connect:8083' },"
        + " tasks: [ "
        + "   { id: 0, state: '"
        + taskState
        + "', worker_id: 'cp-kafka-connect:8083' }"
        + "],"
        + " type: 'source' "
        + "}";
  }
}
