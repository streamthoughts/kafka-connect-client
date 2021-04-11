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
package io.streamthoughts.kafka.connect.client.openapi.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** ConnectorStateInfo */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ConnectorStateInfo {
  public static final String SERIALIZED_NAME_NAME = "name";

  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_CONNECTOR = "connector";

  @SerializedName(SERIALIZED_NAME_CONNECTOR)
  private ConnectorState connector;

  public static final String SERIALIZED_NAME_TASKS = "tasks";

  @SerializedName(SERIALIZED_NAME_TASKS)
  private List<TaskState> tasks = new ArrayList<>();

  public static final String SERIALIZED_NAME_TYPE = "type";

  @SerializedName(SERIALIZED_NAME_TYPE)
  private String type;

  public ConnectorStateInfo name(String name) {

    this.name = name;
    return this;
  }

  /**
   * The name of the connector
   *
   * @return name
   */
  @ApiModelProperty(required = true, value = "The name of the connector")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ConnectorStateInfo connector(ConnectorState connector) {

    this.connector = connector;
    return this;
  }

  /**
   * Get connector
   *
   * @return connector
   */
  @ApiModelProperty(required = true, value = "")
  public ConnectorState getConnector() {
    return connector;
  }

  public void setConnector(ConnectorState connector) {
    this.connector = connector;
  }

  public ConnectorStateInfo tasks(List<TaskState> tasks) {

    this.tasks = tasks;
    return this;
  }

  public ConnectorStateInfo addTasksItem(TaskState tasksItem) {
    this.tasks.add(tasksItem);
    return this;
  }

  /**
   * The states of tasks
   *
   * @return tasks
   */
  @ApiModelProperty(required = true, value = "The states of tasks")
  public List<TaskState> getTasks() {
    return tasks;
  }

  public void setTasks(List<TaskState> tasks) {
    this.tasks = tasks;
  }

  public ConnectorStateInfo type(String type) {

    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   */
  @ApiModelProperty(required = true, value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectorStateInfo connectorStateInfo = (ConnectorStateInfo) o;
    return Objects.equals(this.name, connectorStateInfo.name)
        && Objects.equals(this.connector, connectorStateInfo.connector)
        && Objects.equals(this.tasks, connectorStateInfo.tasks)
        && Objects.equals(this.type, connectorStateInfo.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, connector, tasks, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorStateInfo {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    connector: ").append(toIndentedString(connector)).append("\n");
    sb.append("    tasks: ").append(toIndentedString(tasks)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
