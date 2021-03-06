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

/** TaskState */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class TaskState {
  public static final String SERIALIZED_NAME_ID = "id";

  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;

  public static final String SERIALIZED_NAME_STATE = "state";

  @SerializedName(SERIALIZED_NAME_STATE)
  private State state;

  public static final String SERIALIZED_NAME_WORKER_ID = "worker_id";

  @SerializedName(SERIALIZED_NAME_WORKER_ID)
  private String workerId;

  public static final String SERIALIZED_NAME_MSG = "msg";

  @SerializedName(SERIALIZED_NAME_MSG)
  private String msg;

  public TaskState id(Integer id) {

    this.id = id;
    return this;
  }

  /**
   * The identifier of the Task
   *
   * @return id
   */
  @ApiModelProperty(required = true, value = "The identifier of the Task")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TaskState state(State state) {

    this.state = state;
    return this;
  }

  /**
   * Get state
   *
   * @return state
   */
  @ApiModelProperty(required = true, value = "")
  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public TaskState workerId(String workerId) {

    this.workerId = workerId;
    return this;
  }

  /**
   * The Kafka Connect Worker ID
   *
   * @return workerId
   */
  @ApiModelProperty(required = true, value = "The Kafka Connect Worker ID")
  public String getWorkerId() {
    return workerId;
  }

  public void setWorkerId(String workerId) {
    this.workerId = workerId;
  }

  public TaskState msg(String msg) {

    this.msg = msg;
    return this;
  }

  /**
   * The error trace
   *
   * @return msg
   */
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The error trace")
  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskState taskState = (TaskState) o;
    return Objects.equals(this.id, taskState.id)
        && Objects.equals(this.state, taskState.state)
        && Objects.equals(this.workerId, taskState.workerId)
        && Objects.equals(this.msg, taskState.msg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, state, workerId, msg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TaskState {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    workerId: ").append(toIndentedString(workerId)).append("\n");
    sb.append("    msg: ").append(toIndentedString(msg)).append("\n");
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
