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

import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.openapi.apis.ConnectApi;
import java.util.Objects;

/**
 * The {@code ConnectRestClient} can be used for building a new {@link ConnectApi} for Kafka
 * Connect.
 */
public class ConnectRestClient {

    private final ConnectApi api;

    /**
     * Creates a new {@link ConnectRestClient} instance using default ApiClient.
     *
     * @see Configuration#getDefaultApiClient().
     */
    public ConnectRestClient() {
        this.api = new ConnectApi();
    }

    /**
     * Creates a new {@link ConnectRestClient} instance using the specified {@link ApiClient}.
     *
     * @param apiClient the {@link ApiClient}.
     */
    public ConnectRestClient(final ApiClient apiClient) {
        this.api =
                new ConnectApi(Objects.requireNonNull(apiClient, "apiClient should not be null"));
    }

    /** @return the {@link ConnectApi} instance. */
    public ConnectApi api() {
        return api;
    }
}
