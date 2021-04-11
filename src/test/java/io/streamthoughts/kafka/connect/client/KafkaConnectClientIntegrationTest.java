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

import static io.streamthoughts.kafka.connect.client.ConnectorConfig.CONNECTOR_CLASS_CONFIG;
import static io.streamthoughts.kafka.connect.client.ConnectorConfig.CONNECTOR_TASKS_MAX_CONFIG;
import static io.streamthoughts.kafka.connect.client.ConnectorConfig.CONNECTOR_TOPIC_CONFIG;
import static io.streamthoughts.kafka.connect.client.ConnectorConfig.builder;

import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorPlugin;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorStateInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.State;
import io.streamthoughts.kafka.connect.client.openapi.models.TaskInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.TaskState;
import io.streamthoughts.kafka.connect.client.openapi.models.Version;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KafkaConnectClientIntegrationTest extends AbstractKafkaConnectTest {

  public static final String TEST_CONNECTOR_NAME = "test-connector";

  public static final ConnectorConfig TEST_CONNECTOR_CONFIG =
      builder()
          .withConnectorClass("FileStreamSource")
          .withTasksMax(1)
          .withTopic("connect-test")
          .withConnectorProp("file", "/tmp/test.txt");

  public static final String TYPE_SOURCE = "source";
  public static final String TEST_WORKER_ID = "cp-kafka-connect:8083";
  public static final int TASK_ID = 0;

  private final KafkaConnectRestClient client;

  private final AtomicBoolean createConnectorBeforeTest = new AtomicBoolean(true);

  public KafkaConnectClientIntegrationTest() {
    ApiClient client = Config.fromUrl(getConnectString());
    Configuration.setDefaultApiClient(client);
    this.client = new KafkaConnectRestClient();
  }

  @BeforeEach
  public void setUp() throws Exception {
    if (createConnectorBeforeTest.compareAndSet(true, false)) {
      final CompletableFuture<ConnectorInfo> future =
          client.updateOrCreateConnectorConfig(TEST_CONNECTOR_NAME, TEST_CONNECTOR_CONFIG);
      future.get();
    }
  }

  @Test
  public void should_get_version() throws Exception {
    final Version version = client.getConnectVersion().get();

    Assertions.assertEquals("6.1.1-ccs", version.getVersion());
    Assertions.assertEquals("c209f70c6c2e52ae", version.getCommit());
    Assertions.assertNotNull(version.getKafkaClusterId());
  }

  /*
   * [
   *   {
   *     class: "org.apache.kafka.connect.file.FileStreamSinkConnector",
   *     type: "sink",
   *     version: "6.1.1-ccs"
   *   },
   *   {
   *     class: "org.apache.kafka.connect.file.FileStreamSourceConnector",
   *     type: "source",
   *     version: "6.1.1-ccs"
   *   },
   *   {
   *     class: "org.apache.kafka.connect.mirror.MirrorCheckpointConnector",
   *     type: "source",
   *     version: "1"
   *   },
   *   {
   *     class: "org.apache.kafka.connect.mirror.MirrorHeartbeatConnector",
   *     type: "source",
   *     version: "1"
   *   },
   *   {
   *     class: "org.apache.kafka.connect.mirror.MirrorSourceConnector",
   *     type: "source",
   *     version: "1"
   *   }
   * ]
   */
  @Test
  public void should_list_connector_plugins() throws Exception {
    final List<ConnectorPlugin> plugins = client.listConnectorPlugins().get();
    Assertions.assertNotNull(plugins);
    Assertions.assertFalse(plugins.isEmpty());
    Assertions.assertEquals(5, plugins.size());
    System.out.println(plugins);
  }

  /*
   * [
   * "test-connector"
   * ]
   */
  @Test
  public void should_list_active_connectors() throws Exception {
    final List<String> connectors = client.listConnectors().get();
    Assertions.assertNotNull(connectors);
    Assertions.assertEquals(1, connectors.size());
    Assertions.assertTrue(connectors.contains(TEST_CONNECTOR_NAME));
  }

  /*
   * {
   *   name: "test-connector",
   *   config: {
   *     connector.class: "FileStreamSource",
   *     file: "/tmp/test.txt",
   *     tasks.max: "1",
   *     name: "test-connector",
   *     topic: "connect-test"
   *   },
   *   tasks: [
   *   {
   *     connector: "test-connector",
   *     task: 0
   *    }
   *   ],
   *   type: "source"
   * }
   */
  @Test
  public void should_get_connector_info_given_valid_name() throws Exception {
    final ConnectorInfo connectorInfo = client.getConnectorInfo(TEST_CONNECTOR_NAME).get();
    Assertions.assertNotNull(connectorInfo);
    Assertions.assertEquals(TEST_CONNECTOR_NAME, connectorInfo.getName());
    assertConnectorConfig(connectorInfo.getConfig());
    Assertions.assertNotNull(connectorInfo.getTasks());
    Assertions.assertEquals(1, connectorInfo.getTasks().size());
    Assertions.assertEquals(TEST_CONNECTOR_NAME, connectorInfo.getTasks().get(0).getConnector());
    Assertions.assertEquals(TASK_ID, connectorInfo.getTasks().get(0).getTask());
    Assertions.assertEquals(TYPE_SOURCE, connectorInfo.getType());
  }

  /*
   * {
   * connector.class: "FileStreamSource",
   * file: "/tmp/test.txt",
   * tasks.max: "1",
   * name: "test-connector",
   * topic: "connect-test"
   * }
   */
  @Test
  public void should_get_connector_config() throws Exception {
    final Map<String, String> config = client.getConnectorConfig(TEST_CONNECTOR_NAME).get();
    Assertions.assertNotNull(config);
    assertConnectorConfig(config);
  }

  /*
   *  {
   *     name: "test-connector",
   *     connector: { state: "RUNNING", worker_id: "cp-kafka-connect:8083" },
   *     tasks: [ { id: 0, state: "RUNNING", worker_id: "cp-kafka-connect:8083" }],
   *     type: "source"
   *  }
   */
  @Test
  public void should_get_connector_state() throws Exception {
    final ConnectorStateInfo stateInfo = client.getConnectorStateInfo(TEST_CONNECTOR_NAME).get();
    Assertions.assertNotNull(stateInfo);
    Assertions.assertEquals(TEST_CONNECTOR_NAME, stateInfo.getName());
    Assertions.assertEquals(State.RUNNING, stateInfo.getConnector().getState());
    Assertions.assertEquals(TEST_WORKER_ID, stateInfo.getConnector().getWorkerId());
    Assertions.assertNotNull(stateInfo.getTasks());
    Assertions.assertEquals(State.RUNNING, stateInfo.getTasks().get(0).getState());
    Assertions.assertEquals(TEST_WORKER_ID, stateInfo.getTasks().get(0).getWorkerId());
    Assertions.assertEquals(TASK_ID, stateInfo.getTasks().get(0).getId());
  }

  /*
   * [
   *  {
   *    id: { connector: "test-connector", task: 0 },
   *    config: {
   *      file: "/tmp/test.txt",
   *      task.class: "org.apache.kafka.connect.file.FileStreamSourceTask",
   *      batch.size: "2000",
   *      topic: "connect-test"
   *    }
   *  }
   * ]
   */
  @Test
  public void should_get_connector_task() throws Exception {
    final List<TaskInfo> taskInfos = client.getConnectorTaskInfos(TEST_CONNECTOR_NAME).get();
    Assertions.assertNotNull(taskInfos);
    Assertions.assertEquals(1, taskInfos.size());
  }

  /*
   * {
   * id: 0,
   * state: "RUNNING",
   * worker_id: "cp-kafka-connect:8083"
   * }
   */
  @Test
  public void should_get_connect_task_status() throws ExecutionException, InterruptedException {
    final TaskState taskState = client.getConnectorTaskStatus(TEST_CONNECTOR_NAME, TASK_ID).get();
    Assertions.assertNotNull(taskState);
  }

  private void assertConnectorConfig(Map<String, String> config) {
    final Map<String, String> expected = TEST_CONNECTOR_CONFIG.build();
    Assertions.assertEquals(
        expected.get(CONNECTOR_CLASS_CONFIG), config.get(CONNECTOR_CLASS_CONFIG));
    Assertions.assertEquals(
        expected.get(CONNECTOR_TASKS_MAX_CONFIG), config.get(CONNECTOR_TASKS_MAX_CONFIG));
    Assertions.assertEquals(
        expected.get(CONNECTOR_TOPIC_CONFIG), config.get(CONNECTOR_TOPIC_CONFIG));
  }
}
