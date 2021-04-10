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

import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class AbstractKafkaConnectTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractKafkaConnectTest.class);

    private static final Slf4jLogConsumer LOG_CONSUMER = new Slf4jLogConsumer(LOG);

    private static final int KAFKA_PORT = 9092;

    private static final int CONNECT_PORT = 8083;

    private static final String CONNECT_PLUGIN_PATH = "/usr/share/java/";

    private static final String DOCKER_USERNAME = "confluentinc";
    private static final String DOCKER_CONFLUENT_TAG = "6.1.1";
    private static final String CP_KAFKA_IMAGE = "cp-kafka";
    private static final String CP_CONNECT_IMAGE = "cp-kafka-connect";

    public static Network NETWORK = Network.newNetwork();

    @Container
    public static KafkaContainer KAFKA =
            new KafkaContainer(DockerImageName.parse(getDockerImageName(CP_KAFKA_IMAGE)))
                    .withNetwork(NETWORK);

    public static GenericContainer<?> CONNECT;

    @BeforeAll
    public static void startContainers() {
        CONNECT = createConnectWorkerContainer();
        Stream.of(CONNECT).forEach(GenericContainer::start);
        CONNECT.followOutput(LOG_CONSUMER);
    }

    @AfterAll
    public static void stopContainers() {
        Stream.of(CONNECT).forEach(GenericContainer::stop);
    }

    private static GenericContainer<?> createConnectWorkerContainer() {
        return new GenericContainer<>(getDockerImageName(CP_CONNECT_IMAGE))
                .withNetwork(NETWORK)
                .withExposedPorts(CONNECT_PORT)
                .withNetworkAliases(CP_CONNECT_IMAGE)
                .withEnv("CONNECT_REST_PORT", String.valueOf(CONNECT_PORT))
                .withEnv("CONNECT_GROUP_ID", "testGivenDefaultProperties")
                .withEnv("CONNECT_CONFIG_STORAGE_TOPIC", "testGivenDefaultProperties-configDef")
                .withEnv("CONNECT_CONFIG_STORAGE_REPLICATION_FACTOR", "1")
                .withEnv("CONNECT_OFFSET_STORAGE_TOPIC", "testGivenDefaultProperties-offset")
                .withEnv("CONNECT_OFFSET_STORAGE_REPLICATION_FACTOR", "1")
                .withEnv("CONNECT_STATUS_STORAGE_TOPIC", "testGivenDefaultProperties-status")
                .withEnv("CONNECT_STATUS_STORAGE_REPLICATION_FACTOR", "1")
                .withEnv("CONNECT_KEY_CONVERTER", "org.apache.kafka.connect.json.JsonConverter")
                .withEnv("CONNECT_VALUE_CONVERTER", "org.apache.kafka.connect.json.JsonConverter")
                .withEnv(
                        "CONNECT_INTERNAL_KEY_CONVERTER",
                        "org.apache.kafka.connect.json.JsonConverter")
                .withEnv(
                        "CONNECT_INTERNAL_VALUE_CONVERTER",
                        "org.apache.kafka.connect.json.JsonConverter")
                .withEnv("CONNECT_LOG4J_ROOT_LOGLEVEL", "WARN")
                .withEnv("CONNECT_REST_ADVERTISED_HOST_NAME", CP_CONNECT_IMAGE)
                .withEnv("CONNECT_PLUGIN_PATH", CONNECT_PLUGIN_PATH)
                .withEnv(
                        "CONNECT_BOOTSTRAP_SERVERS",
                        KAFKA.getNetworkAliases().get(0) + ":" + KAFKA_PORT)
                .waitingFor(Wait.forHttp("/"));
    }

    static String getDockerImageName(final String name) {
        return DOCKER_USERNAME + "/" + name + ":" + DOCKER_CONFLUENT_TAG;
    }

    static String getBootstrapServer() {
        return KAFKA.getBootstrapServers();
    }

    static String getConnectString() {
        return "http://"
                + CONNECT.getContainerIpAddress()
                + ":"
                + CONNECT.getMappedPort(CONNECT_PORT);
    }
}
