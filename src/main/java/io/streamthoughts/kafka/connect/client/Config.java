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
import io.streamthoughts.kafka.connect.client.security.UsernamePasswordAuthenticator;
import java.util.Optional;

public class Config {

    public static final String ENV_SERVICE_HOST = "KAFKA_CONNECT_SERVICE_HOST";
    public static final String ENV_SERVICE_PORT = "KAFKA_CONNECT_SERVICE_PORT";

    public static final String DEFAULT_FALLBACK_HOST = "localhost";
    public static final String DEFAULT_FALLBACK_PORT = "8083";

    public static ApiClient fromUrl(final String url) {
        return fromUrl(url, false);
    }

    public static ApiClient fromUrl(final String url, final boolean validateSSL) {
        return new ApiClient().setBasePath(url).setVerifyingSsl(validateSSL);
    }

    public static ApiClient fromUserPassword(
            final String url, final String user, final String password) {
        return fromUserPassword(url, user, password, true);
    }

    public static ApiClient fromUserPassword(
            final String url, final String user, final String password, final boolean validateSSL) {

        final UsernamePasswordAuthenticator.Credential credential =
                new UsernamePasswordAuthenticator.Credential(user, password);
        return ClientApiBuilder.builder()
                .withBasePath(url)
                .withAuthenticator(new UsernamePasswordAuthenticator(() -> credential))
                .withVerifyingSsl(validateSSL)
                .build();
    }

    /**
     * Creates a default {@link ApiClient} given the following rules:
     *
     * <ul>
     *   <li>If {@code KAFKA_CONNECT_SERVICE_HOST} environment variable is defined, use that host.
     *   <li>If {@code KAFKA_CONNECT_SERVICE_PORT} environment variable is defined, use that port.
     *   <li>Otherwise to localhost:8083 as a last resort.
     * </ul>
     *
     * @return a new {@link ApiClient} given the previously described rules.
     */
    public static ApiClient defaultClient() {
        final String serviceHost =
                Optional.ofNullable(System.getenv(ENV_SERVICE_HOST)).orElse(DEFAULT_FALLBACK_HOST);

        final String servicePort =
                Optional.ofNullable(System.getenv(ENV_SERVICE_PORT)).orElse(DEFAULT_FALLBACK_PORT);
        final String basePath = "http://" + serviceHost + ":" + servicePort;
        return fromUrl(basePath);
    }
}
