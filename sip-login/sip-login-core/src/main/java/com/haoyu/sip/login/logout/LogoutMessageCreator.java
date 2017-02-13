/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
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
package com.haoyu.sip.login.logout;

/**
 * Contract that defines the format of the logout message sent to a client to indicate
 * that an SSO session has terminated.
 * @author Misagh Moayyed
 * @since 4.0
 */
public interface LogoutMessageCreator {
    /**
     * Builds the logout message to be sent.
     *
     * @param request the request
     * @return the message. Message may or may not be encoded.
     */
    String create(LogoutRequest request);
}