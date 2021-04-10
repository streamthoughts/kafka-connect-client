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

import static io.streamthoughts.kafka.connect.client.Config.DEFAULT_FALLBACK_HOST;
import static io.streamthoughts.kafka.connect.client.Config.DEFAULT_FALLBACK_PORT;

import io.streamthoughts.kafka.connect.client.openapi.ApiClient;
import io.streamthoughts.kafka.connect.client.security.SSLContextFactory;
import io.streamthoughts.kafka.connect.client.security.SSLUtils;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Authenticator;
import okhttp3.OkHttpClient;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.logging.HttpLoggingInterceptor;

/** The {@code ClientApiBuilder} can be used for building a new {@link OkHttpClient} instance. */
public class ClientApiBuilder {

    public static final AllowAllHostNameVerifier NO_HOST_NAME_VERIFIER =
            new AllowAllHostNameVerifier();

    private boolean debugging = false;

    private HttpLoggingInterceptor loggingInterceptor;

    private OkHttpClient httpClient;
    private boolean verifyingSsl = true;
    private KeyManager[] keyManagers = {};
    private TrustManager[] trustManagers = {};
    private SSLContext sslContext;

    private String basePath = "http://" + DEFAULT_FALLBACK_HOST + ":" + DEFAULT_FALLBACK_PORT;

    /**
     * Helper method to create a new {@link ClientApiBuilder} instance.
     *
     * @return a new {@link ClientApiBuilder} instance.
     */
    public static ClientApiBuilder builder() {
        return new ClientApiBuilder();
    }

    private ClientApiBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        httpClient = builder.build();
    }

    public ClientApiBuilder withBasePath(final String basePath) {
        this.basePath = basePath;
        return this;
    }

    public ClientApiBuilder withVerifyingSsl(final boolean verifyingSsl) {
        this.verifyingSsl = verifyingSsl;
        applySslConfigs();
        return this;
    }

    public ClientApiBuilder withSslContext(final SSLContext sslContext) {
        this.sslContext = sslContext;
        applySslConfigs();
        return this;
    }

    public ClientApiBuilder withSslKeyManagers(final KeyManager[] keyManagers) {
        this.keyManagers = Arrays.copyOf(keyManagers, keyManagers.length);
        applySslConfigs();
        return this;
    }

    public ClientApiBuilder withSslTrustManagers(final TrustManager[] trustManagers) {
        this.trustManagers = Arrays.copyOf(trustManagers, trustManagers.length);
        applySslConfigs();
        return this;
    }

    public ClientApiBuilder withAuthenticator(final Authenticator authenticator) {
        httpClient = httpClient.newBuilder().authenticator(authenticator).build();
        return this;
    }

    /**
     * Enable/disable debugging for this API client.
     *
     * @param debugging To enable (true) or disable (false) debugging
     * @return this {@link ClientApiBuilder}
     */
    public ClientApiBuilder withDebugging(boolean debugging) {
        if (debugging != this.debugging) {
            if (debugging) {
                loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient = httpClient.newBuilder().addInterceptor(loggingInterceptor).build();
            } else {
                httpClient.interceptors().remove(loggingInterceptor);
                loggingInterceptor = null;
            }
        }
        this.debugging = debugging;
        return this;
    }

    /**
     * Sets the connect timeout (in milliseconds). A value of 0 means no timeout, otherwise values
     * must be between 1 and {@link Integer#MAX_VALUE}.
     *
     * @param connectionTimeout connection timeout in milliseconds
     * @return this {@link ClientApiBuilder}
     */
    public ClientApiBuilder withConnectTimeout(final int connectionTimeout) {
        httpClient =
                httpClient
                        .newBuilder()
                        .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                        .build();
        return this;
    }

    /**
     * Sets the read timeout (in milliseconds). A value of 0 means no timeout, otherwise values must
     * be between 1 and {@link Integer#MAX_VALUE}.
     *
     * @param readTimeout read timeout in milliseconds
     * @return this {@link ClientApiBuilder}
     */
    public ClientApiBuilder withReadTimeout(int readTimeout) {
        httpClient =
                httpClient.newBuilder().readTimeout(readTimeout, TimeUnit.MILLISECONDS).build();
        return this;
    }

    /**
     * Sets the write timeout (in milliseconds). A value of 0 means no timeout, otherwise values
     * must be between 1 and {@link Integer#MAX_VALUE}.
     *
     * @param writeTimeout connection timeout in milliseconds
     * @return this {@link ClientApiBuilder}
     */
    public ClientApiBuilder withWriteTimeout(int writeTimeout) {
        httpClient =
                httpClient.newBuilder().writeTimeout(writeTimeout, TimeUnit.MILLISECONDS).build();
        return this;
    }

    /**
     * Builds a new {@link ApiClient}.
     *
     * @return a {@link ApiClient} instance.
     */
    public ApiClient build() {
        final OkHttpClient httpClient = this.httpClient.newBuilder().build();
        return new ApiClient(httpClient).setBasePath(basePath);
    }

    private void applySslConfigs() {
        HostnameVerifier hostnameVerifier =
                verifyingSsl ? OkHostnameVerifier.INSTANCE : NO_HOST_NAME_VERIFIER;
        SSLContext sslContext = this.sslContext;

        if (sslContext == null) {
            TrustManager[] trustManagers;
            if (!verifyingSsl) {
                trustManagers =
                        new TrustManager[] {
                            new X509TrustManager() {
                                @Override
                                public void checkClientTrusted(
                                        X509Certificate[] chain, String authType) {}

                                @Override
                                public void checkServerTrusted(
                                        X509Certificate[] chain, String authType) {}

                                @Override
                                public X509Certificate[] getAcceptedIssuers() {
                                    return new X509Certificate[] {};
                                }
                            }
                        };
            } else {
                trustManagers = this.trustManagers;
            }
            sslContext = new SSLContextFactory().getSSLContext(keyManagers, trustManagers);
        }

        httpClient =
                httpClient
                        .newBuilder()
                        .sslSocketFactory(
                                sslContext.getSocketFactory(),
                                SSLUtils.getX509TrustManager(trustManagers))
                        .hostnameVerifier(hostnameVerifier)
                        .build();
    }

    /** A {@link HostnameVerifier} that accept all certificates. */
    public static class AllowAllHostNameVerifier implements HostnameVerifier {

        /** {@inheritDoc} */
        @Override
        public boolean verify(final String hostname, final SSLSession sslSession) {
            return true;
        }
    }
}
