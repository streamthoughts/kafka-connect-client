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

/** ConnectorStatus */
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2021-04-10T15:10:20.847879+02:00[Europe/Paris]")
public class ConnectorStatus {
    public static final String SERIALIZED_NAME_NAME = "name";

    @SerializedName(SERIALIZED_NAME_NAME)
    private String name;

    public static final String SERIALIZED_NAME_CONNECTOR = "connector";

    @SerializedName(SERIALIZED_NAME_CONNECTOR)
    private ConnectorStatus connector;

    public static final String SERIALIZED_NAME_TASKS = "tasks";

    @SerializedName(SERIALIZED_NAME_TASKS)
    private List<TaskState> tasks = new ArrayList<>();

    public ConnectorStatus name(String name) {

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

    public ConnectorStatus connector(ConnectorStatus connector) {

        this.connector = connector;
        return this;
    }

    /**
     * Get connector
     *
     * @return connector
     */
    @ApiModelProperty(required = true, value = "")
    public ConnectorStatus getConnector() {
        return connector;
    }

    public void setConnector(ConnectorStatus connector) {
        this.connector = connector;
    }

    public ConnectorStatus tasks(List<TaskState> tasks) {

        this.tasks = tasks;
        return this;
    }

    public ConnectorStatus addTasksItem(TaskState tasksItem) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectorStatus connectorStatus = (ConnectorStatus) o;
        return Objects.equals(this.name, connectorStatus.name)
                && Objects.equals(this.connector, connectorStatus.connector)
                && Objects.equals(this.tasks, connectorStatus.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, connector, tasks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConnectorStatus {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    connector: ").append(toIndentedString(connector)).append("\n");
        sb.append("    tasks: ").append(toIndentedString(tasks)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
