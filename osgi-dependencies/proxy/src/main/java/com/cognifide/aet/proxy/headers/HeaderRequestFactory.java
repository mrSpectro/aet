/**
 * AET
 *
 * Copyright (C) 2013 Cognifide Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.cognifide.aet.proxy.headers;

import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import com.cognifide.aet.proxy.bmpc.BMPCProxy;

public class HeaderRequestFactory {

    private final BMPCProxy server;

    public HeaderRequestFactory(BMPCProxy server) {
        this.server = server;
    }

    public HttpPost create(String name, String value, Boolean override) throws UnsupportedEncodingException, URISyntaxException {
        HeaderRequestStrategy headerRequestStrategy;
        if (override) {
            headerRequestStrategy = new OverrideHeader(server.getAPIHost(), server.getAPIPort(),
                    server.getProxyPort());
        } else {
            headerRequestStrategy = new AddHeader(server.getAPIHost(), server.getAPIPort(),
                    server.getProxyPort());
        }
        return headerRequestStrategy.createRequest(name, value);
    }
}
