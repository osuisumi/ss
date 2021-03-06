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
package com.haoyu.sip.login.auth.handler.support;

import java.security.GeneralSecurityException;
import java.util.List;















import javax.security.auth.login.AccountNotFoundException;
import javax.validation.constraints.NotNull;

import com.haoyu.sip.login.Message;
import com.haoyu.sip.login.auth.BasicCredentialMetaData;
import com.haoyu.sip.login.auth.Credential;
import com.haoyu.sip.login.auth.HandlerResult;
import com.haoyu.sip.login.auth.PreventedException;
import com.haoyu.sip.login.auth.UsernamePasswordCredential;
import com.haoyu.sip.login.auth.handler.NoOpPrincipalNameTransformer;
import com.haoyu.sip.login.auth.handler.PasswordEncoder;
import com.haoyu.sip.login.auth.handler.PlainTextPasswordEncoder;
import com.haoyu.sip.login.auth.handler.PrincipalNameTransformer;
import com.haoyu.sip.login.auth.principal.Principal;
import com.haoyu.sip.login.auth.support.PasswordPolicyConfiguration;

/**
 * Abstract class to override supports so that we don't need to duplicate the
 * check for UsernamePasswordCredential.
 *
 * @author Scott Battaglia
 * @author Marvin S. Addison
 *
 * @since 3.0
 */
public abstract class AbstractUsernamePasswordAuthenticationHandler extends
    AbstractPreAndPostProcessingAuthenticationHandler {

    /**
     * PasswordEncoder to be used by subclasses to encode passwords for
     * comparing against a resource.
     */
    @NotNull
    private PasswordEncoder passwordEncoder = new PlainTextPasswordEncoder();

    @NotNull
    private PrincipalNameTransformer principalNameTransformer = new NoOpPrincipalNameTransformer();

    /** The password policy configuration to be used by extensions. */
    private PasswordPolicyConfiguration passwordPolicyConfiguration;
    
    /** {@inheritDoc} */
    @Override
    protected final HandlerResult doAuthentication(final Credential credential)
            throws GeneralSecurityException, PreventedException {
        final UsernamePasswordCredential userPass = (UsernamePasswordCredential) credential;
        if (userPass.getUsername() == null) {
            throw new AccountNotFoundException("Username is null.");
        }
        
        final String transformedUsername= this.principalNameTransformer.transform(userPass.getUsername());
        if (transformedUsername == null) {
            throw new AccountNotFoundException("Transformed username is null.");
        }
        userPass.setUsername(transformedUsername);
        return authenticateUsernamePasswordInternal(userPass);
    }

    /**
     * Authenticates a username/password credential by an arbitrary strategy.
     *
     * @param transformedCredential the credential object bearing the transformed username and password.
     *
     * @return HandlerResult resolved from credential on authentication success or null if no principal could be resolved
     * from the credential.
     *
     * @throws GeneralSecurityException On authentication failure.
     * @throws PreventedException On the indeterminate case when authentication is prevented.
     */
    protected abstract HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential transformedCredential)
            throws GeneralSecurityException, PreventedException;

    /**
     * Method to return the PasswordEncoder to be used to encode passwords.
     *
     * @return the PasswordEncoder associated with this class.
     */
    protected final PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }

    protected final PrincipalNameTransformer getPrincipalNameTransformer() {
        return this.principalNameTransformer;
    }
    
    protected final PasswordPolicyConfiguration getPasswordPolicyConfiguration() {
        return this.passwordPolicyConfiguration;
    }

    /**
     * Helper method to construct a handler result
     * on successful authentication events.
     *
     * @param credential the credential on which the authentication was successfully performed.
     * Note that this credential instance may be different from what was originally provided
     * as transformation of the username may have occurred, if one is in fact defined.
     * @param principal the resolved principal
     * @param warnings the warnings
     * @return the constructed handler result
     */
    protected final HandlerResult createHandlerResult(final Credential credential, final Principal principal,
            final List<Message> warnings) {
        return new HandlerResult(this, new BasicCredentialMetaData(credential), principal, warnings);
    }
    /**
     * Sets the PasswordEncoder to be used with this class.
     *
     * @param passwordEncoder the PasswordEncoder to use when encoding
     * passwords.
     */
    public final void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public final void setPrincipalNameTransformer(final PrincipalNameTransformer principalNameTransformer) {
        this.principalNameTransformer = principalNameTransformer;
    }
    
    public final void setPasswordPolicyConfiguration(final PasswordPolicyConfiguration passwordPolicyConfiguration) {
        this.passwordPolicyConfiguration = passwordPolicyConfiguration;
    }

    /**
     * @return True if credential is a {@link UsernamePasswordCredential}, false otherwise.
     */
    @Override
    public boolean supports(final Credential credential) {
        return credential instanceof UsernamePasswordCredential;
    }
}
