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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** TaskInfo */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TaskInfo {
  public static final String SERIALIZED_NAME_ID = "id";

  @SerializedName(SERIALIZED_NAME_ID)
  private ConnectorTaskId id;

  public static final String SERIALIZED_NAME_CONFIG = "config";

  @SerializedName(SERIALIZED_NAME_CONFIG)
  private Map<String, String> config = new HashMap<>();

  public TaskInfo id(ConnectorTaskId id) {

    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @ApiModelProperty(required = true, value = "")
  public ConnectorTaskId getId() {
    return id;
  }

  public void setId(ConnectorTaskId id) {
    this.id = id;
  }

  public TaskInfo config(Map<String, String> config) {

    this.config = config;
    return this;
  }

  public TaskInfo putConfigItem(String key, String configItem) {
    this.config.put(key, configItem);
    return this;
  }

  /**
   * Configuration parameters for the task.
   *
   * @return config
   */
  @ApiModelProperty(required = true, value = "Configuration parameters for the task.")
  public Map<String, String> getConfig() {
    return config;
  }

  public void setConfig(Map<String, String> config) {
    this.config = config;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskInfo taskInfo = (TaskInfo) o;
    return Objects.equals(this.id, taskInfo.id) && Objects.equals(this.config, taskInfo.config);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, config);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskInfo {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    config: ").append(toIndentedString(config)).append("\n");
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
