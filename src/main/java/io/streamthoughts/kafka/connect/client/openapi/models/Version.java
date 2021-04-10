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

/** Version */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class Version {
  public static final String SERIALIZED_NAME_VERSION = "version";

  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;

  public static final String SERIALIZED_NAME_COMMIT = "commit";

  @SerializedName(SERIALIZED_NAME_COMMIT)
  private String commit;

  public static final String SERIALIZED_NAME_KAFKA_CLUSTER_ID = "kafka_cluster_id";

  @SerializedName(SERIALIZED_NAME_KAFKA_CLUSTER_ID)
  private String kafkaClusterId;

  public Version version(String version) {

    this.version = version;
    return this;
  }

  /**
   * Connect worker version
   *
   * @return version
   */
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Connect worker version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public Version commit(String commit) {

    this.commit = commit;
    return this;
  }

  /**
   * Git commit ID
   *
   * @return commit
   */
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Git commit ID")
  public String getCommit() {
    return commit;
  }

  public void setCommit(String commit) {
    this.commit = commit;
  }

  public Version kafkaClusterId(String kafkaClusterId) {

    this.kafkaClusterId = kafkaClusterId;
    return this;
  }

  /**
   * Kafka cluster ID
   *
   * @return kafkaClusterId
   */
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Kafka cluster ID")
  public String getKafkaClusterId() {
    return kafkaClusterId;
  }

  public void setKafkaClusterId(String kafkaClusterId) {
    this.kafkaClusterId = kafkaClusterId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Version version = (Version) o;
    return Objects.equals(this.version, version.version)
        && Objects.equals(this.commit, version.commit)
        && Objects.equals(this.kafkaClusterId, version.kafkaClusterId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, commit, kafkaClusterId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Version {\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    commit: ").append(toIndentedString(commit)).append("\n");
    sb.append("    kafkaClusterId: ").append(toIndentedString(kafkaClusterId)).append("\n");
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
