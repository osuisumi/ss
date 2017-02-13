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
package com.haoyu.sip.login.adaptors.jdbc;

import java.security.GeneralSecurityException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.haoyu.sip.login.auth.HandlerResult;
import com.haoyu.sip.login.auth.IncorrectPasswordException;
import com.haoyu.sip.login.auth.PreventedException;
import com.haoyu.sip.login.auth.QrCodeCredential;
import com.haoyu.sip.login.auth.UsernamePasswordCredential;
import com.haoyu.sip.login.auth.principal.SimplePrincipal;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

/**
 * Class that if provided a query that returns a password (parameter of query
 * must be username) will compare that password to a translated version of the
 * password provided by the user. If they match, then authentication succeeds.
 * Default password translator is plaintext translator.
 *
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @author Marvin S. Addison
 *
 * @since 3.0
 */
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    @NotNull
    private String sql;

    /** {@inheritDoc} */
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {

        final String username = credential.getUsername();
        final String encryptedPassword = this.getPasswordEncoder().encode(credential.getPassword());
        try {
        	//针对参数占位符动态传入参数，主要处理输入的用户名可以用于多个字段匹配的情况
        	int placeHolderCount = StringUtils.countMatches(this.sql, "?");
        	Object[] parameters  = new String[placeHolderCount];
    		for(int i=0;i<parameters.length;i++){
    			parameters[i] = username;
    		}
    		final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, parameters);
        	if (!(credential instanceof QrCodeCredential)&&!dbPassword.equals(encryptedPassword)) {
                throw new IncorrectPasswordException("Password does not match value on record.");
            }
        	
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }
        return createHandlerResult(credential, new SimplePrincipal(username), null);
    }

    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }
}
