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

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ConnectorConfig {

  public static final String CONNECTOR_CLASS_CONFIG = "connector.class";
  public static final String CONNECTOR_TASKS_MAX_CONFIG = "tasks.max";
  public static final String CONNECTOR_TOPIC_CONFIG = "topic";

  private final Map<String, Object> properties;

  public static ConnectorConfig builder() {
    return new ConnectorConfig();
  }

  /** Creates a new {@link ConnectorConfig} instance. */
  private ConnectorConfig() {
    this(new TreeMap<>());
  }

  private ConnectorConfig(final Map<String, Object> properties) {
    this.properties = properties;
  }

  public ConnectorConfig withTasksMax(final int tasksMax) {
    return withConnectorProp(CONNECTOR_TASKS_MAX_CONFIG, tasksMax);
  }

  public ConnectorConfig withTopic(final String topic) {
    return withConnectorProp(CONNECTOR_TOPIC_CONFIG, topic);
  }

  public ConnectorConfig withConnectorClass(final String connectorClass) {
    return withConnectorProp(CONNECTOR_CLASS_CONFIG, connectorClass);
  }

  public ConnectorConfig withConnectorProp(final String key, final Object value) {
    final HashMap<String, Object> config = new HashMap<>(properties);
    config.put(key, value);
    return new ConnectorConfig(config);
  }

  public Map<String, String> build() {
    return properties.entrySet().stream()
        .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, e -> e.getValue().toString()));
  }

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return "Config(" + new TreeMap<>(properties) + ")";
  }
}
