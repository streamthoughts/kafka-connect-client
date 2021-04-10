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

import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.ApiException;
import io.streamthoughts.kafka.connect.client.openapi.Configuration;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorInfo;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorPlugin;
import io.streamthoughts.kafka.connect.client.openapi.models.ConnectorStatus;
import io.streamthoughts.kafka.connect.client.openapi.models.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KafkaConnectClientIntegrationTest extends AbstractKafkaConnectTest {

    public static final String TEST_CONNECTOR_NAME = "test-connector";

    private final ConnectRestClient client;

    private final AtomicBoolean createConnectorBeforeTest = new AtomicBoolean(true);

    public KafkaConnectClientIntegrationTest() {
        ApiClient client = Config.fromUrl(getConnectString());
        Configuration.setDefaultApiClient(client);
        this.client = new ConnectRestClient();
    }

    @BeforeEach
    public void setUp() throws ApiException {
        if (createConnectorBeforeTest.compareAndSet(true, false)) {
            Map<String, String> connectorConfig = new HashMap<>();
            connectorConfig.put("connector.class", "FileStreamSource");
            connectorConfig.put("tasks.max", "1");
            connectorConfig.put("file", "/tmp/test.txt");
            connectorConfig.put("topic", "connect-test");
            client.api().updateOrCreateConnectorConfig(TEST_CONNECTOR_NAME, connectorConfig);
        }
    }

    @Test
    public void should_get_version() throws ApiException {
        final Root version = client.api().getConnectVersion();

        Assertions.assertEquals("6.1.1-ccs", version.getVersion());
        Assertions.assertEquals("c209f70c6c2e52ae", version.getCommit());
        Assertions.assertNotNull(version.getKafkaClusterId());
    }

    @Test
    public void should_list_connector_plugins() throws ApiException {
        final List<ConnectorPlugin> plugins = client.api().listConnectorPlugins();
        Assertions.assertNotNull(plugins);
        Assertions.assertFalse(plugins.isEmpty());
        Assertions.assertEquals(5, plugins.size());
        System.out.println(plugins);
    }

    @Test
    public void should_list_active_connectors() throws ApiException {
        final List<String> connectors = client.api().listConnectors();
        Assertions.assertNotNull(connectors);
        Assertions.assertEquals(1, connectors.size());
        Assertions.assertTrue(connectors.contains(TEST_CONNECTOR_NAME));
    }

    @Test
    public void should_get_connector_info_given_valid_name() throws ApiException {
        final ConnectorInfo connector = client.api().getConnectorInfo(TEST_CONNECTOR_NAME);
        Assertions.assertNotNull(connector);
    }

    @Test
    public void should_get_connector_config() throws ApiException {
        final Map<String, String> config = client.api().getConnectorConfig(TEST_CONNECTOR_NAME);
        Assertions.assertNotNull(config);
    }

    @Test
    public void should_get_connector_state() throws ApiException {
        final ConnectorStatus connectorStatus =
                client.api().getConnectorStatus(TEST_CONNECTOR_NAME);
        Assertions.assertNotNull(connectorStatus);
    }
}
