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
package com.haoyu.sip.login.auth.principal;

import com.haoyu.sip.login.auth.Credential;

/**
 * Provides the most basic means of principal resolution by mapping
 * {@link com.haoyu.sip.login.authentication.Credential#getId()} onto
 * {@link com.haoyu.sip.login.authentication.principal.Principal#getId()}.
 *
 * @author Marvin S. Addison
 * @since 4.0
 */
public class BasicPrincipalResolver implements PrincipalResolver {

    @Override
    public Principal resolve(final Credential credential) {
        return new SimplePrincipal(credential.getId());
    }

    @Override
    public boolean supports(final Credential credential) {
        return credential.getId() != null;
    }
}
