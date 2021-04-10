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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/** State */
@JsonAdapter(State.Adapter.class)
public enum State {
  UNASSIGNED("UNASSIGNED"),

  RUNNING("RUNNING"),

  PAUSE("PAUSE"),

  FAILED("FAILED"),

  DESTROYED("DESTROYED");

  private String value;

  State(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static State fromValue(String value) {
    for (State b : State.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<State> {
    @Override
    public void write(final JsonWriter jsonWriter, final State enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public State read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return State.fromValue(value);
    }
  }
}
