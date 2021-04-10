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

/** ConnectorPlugin */
@javax.annotation.Generated(
        value = "org.openapitools.codegen.languages.JavaClientCodegen",
        date = "2021-04-10T15:10:20.847879+02:00[Europe/Paris]")
public class ConnectorPlugin {
    public static final String SERIALIZED_NAME_PROPERTY_CLASS = "class";

    @SerializedName(SERIALIZED_NAME_PROPERTY_CLASS)
    private String propertyClass;

    public static final String SERIALIZED_NAME_VERSION = "version";

    @SerializedName(SERIALIZED_NAME_VERSION)
    private String version;

    public static final String SERIALIZED_NAME_TYPE = "type";

    @SerializedName(SERIALIZED_NAME_TYPE)
    private String type;

    public ConnectorPlugin propertyClass(String propertyClass) {

        this.propertyClass = propertyClass;
        return this;
    }

    /**
     * The connector class name.
     *
     * @return propertyClass
     */
    @ApiModelProperty(required = true, value = "The connector class name.")
    public String getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(String propertyClass) {
        this.propertyClass = propertyClass;
    }

    public ConnectorPlugin version(String version) {

        this.version = version;
        return this;
    }

    /**
     * The connector version.
     *
     * @return version
     */
    @ApiModelProperty(required = true, value = "The connector version.")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ConnectorPlugin type(String type) {

        this.type = type;
        return this;
    }

    /**
     * The connector type.
     *
     * @return type
     */
    @ApiModelProperty(required = true, value = "The connector type.")
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
        ConnectorPlugin connectorPlugin = (ConnectorPlugin) o;
        return Objects.equals(this.propertyClass, connectorPlugin.propertyClass)
                && Objects.equals(this.version, connectorPlugin.version)
                && Objects.equals(this.type, connectorPlugin.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyClass, version, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ConnectorPlugin {\n");
        sb.append("    propertyClass: ").append(toIndentedString(propertyClass)).append("\n");
        sb.append("    version: ").append(toIndentedString(version)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
