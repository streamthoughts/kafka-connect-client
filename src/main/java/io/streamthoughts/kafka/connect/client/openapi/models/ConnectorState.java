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

/** ConnectorState */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ConnectorState {
  public static final String SERIALIZED_NAME_WORKER_ID = "worker_id";

  @SerializedName(SERIALIZED_NAME_WORKER_ID)
  private String workerId;

  public static final String SERIALIZED_NAME_STATE = "state";

  @SerializedName(SERIALIZED_NAME_STATE)
  private State state;

  public static final String SERIALIZED_NAME_MSG = "msg";

  @SerializedName(SERIALIZED_NAME_MSG)
  private String msg;

  public ConnectorState workerId(String workerId) {

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

  public ConnectorState state(State state) {

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

  public ConnectorState msg(String msg) {

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
    ConnectorState connectorState = (ConnectorState) o;
    return Objects.equals(this.workerId, connectorState.workerId)
        && Objects.equals(this.state, connectorState.state)
        && Objects.equals(this.msg, connectorState.msg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(workerId, state, msg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectorState {\n");
    sb.append("    workerId: ").append(toIndentedString(workerId)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
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
