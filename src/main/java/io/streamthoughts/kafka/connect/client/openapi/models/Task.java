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
import java.util.Objects;

/** Task */
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2021-04-10T15:10:20.847879+02:00[Europe/Paris]")
public class Task {
    public static final String SERIALIZED_NAME_CONNECTOR = "connector";

    @SerializedName(SERIALIZED_NAME_CONNECTOR)
    private String connector;

    public static final String SERIALIZED_NAME_TASK = "task";

    @SerializedName(SERIALIZED_NAME_TASK)
    private Integer task;

    public Task connector(String connector) {

        this.connector = connector;
        return this;
    }

    /**
     * The name of the connector the task belongs to.
     *
     * @return connector
     */
    @ApiModelProperty(required = true, value = "The name of the connector the task belongs to.")
    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public Task task(Integer task) {

        this.task = task;
        return this;
    }

    /**
     * Task ID within the connector.
     *
     * @return task
     */
    @ApiModelProperty(required = true, value = "Task ID within the connector.")
    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(this.connector, task.connector)
                && Objects.equals(this.task, task.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connector, task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Task {\n");
        sb.append("    connector: ").append(toIndentedString(connector)).append("\n");
        sb.append("    task: ").append(toIndentedString(task)).append("\n");
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
