/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.synapse.samples.framework.tests.transport;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpStatus;
import org.apache.synapse.samples.framework.SynapseTestCase;
import org.apache.synapse.samples.framework.clients.BasicHttpClient;
import org.apache.synapse.samples.framework.clients.HttpResponse;

public class Sample10102 extends SynapseTestCase {

    public static final byte[] TEST_PAYLOAD = "<test>foo</test>".getBytes();

    public Sample10102() {
        super(10102);
    }

    public void testSingleClientCertForRevocation() throws Exception {

        BasicHttpClient client = new BasicHttpClient();
        HttpResponse response = client.doPostWithCert("https://localhost:8243/test/order",
                TEST_PAYLOAD, "application/xml",
                FilenameUtils.normalize(System.getProperty("user.dir")
                        + "/modules/integration/src/test/resources/transport.jks"),
                FilenameUtils.normalize(System.getProperty("user.dir") +
                        "/modules/integration/src/test/resources/trust.jks"), "wso2carbon",
                "password");
        assertEquals(HttpStatus.SC_ACCEPTED, response.getStatus());
    }
}
