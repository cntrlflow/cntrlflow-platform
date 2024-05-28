package com.cntrlflow.server.service;

import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cntrlflow.server.model.LoginRequest;
import com.cntrlflow.server.utility.HashUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthService {

    @Value("${cntrlflow.auth.method}")
    private String authMethod;

    @Value("${cntrlflow.ldap.ldapUrl}")
    private String ldapUrl;

    @Value("${cntrlflow.ldap.ldapBaseDn}")
    private String ldapBaseDn;

    @Value("${cntrlflow.ldap.security}")
    private String securityProtocol;

    @Value("${cntrlflow.default.username}")
    private String defaultUsername;

    @Value("${cntrlflow.default.password}")
    private String defaultPassword;

    @Autowired
    private HashUtil hashUtil;

    public boolean authenticateUser(LoginRequest loginRequest) {
        //log.info("[cntrlflow] authenticateUser");
        boolean result = false;

        switch(authMethod) {
            case "ldap": 
                result = ldapAuthentication(loginRequest);
                break;

            case "database": 
                result = databaseAuthentication(loginRequest);
                break;

            case "default": 
                result = defaultAuthentication(loginRequest);
                break;
        }

        return result;
    }

    private boolean ldapAuthentication(LoginRequest loginRequest) {
        // Set up LDAP context
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, ldapUrl);
            env.put(Context.SECURITY_PRINCIPAL, loginRequest.getUsername());
            env.put(Context.SECURITY_CREDENTIALS, loginRequest.getPassword());

            if ("ssl".equalsIgnoreCase(securityProtocol)) {
                 env.put(Context.SECURITY_PROTOCOL, "ssl");
            }

            DirContext ctx = new InitialDirContext(env);

            // Perform an LDAP query (e.g., list all entries)
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            //NamingEnumeration<SearchResult> results = ctx.search(ldapBaseDn, "(objectClass=*)", searchControls);
            ctx.search(ldapBaseDn, "(objectClass=*)", searchControls);

            // Close the LDAP context
            ctx.close();

            return true; // LDAP or LDAPS query succeeded
        } catch (Exception e) {
            e.printStackTrace();
            return false; // LDAP or LDAPS query failed
        }
    }

    private boolean databaseAuthentication(LoginRequest loginRequest) {
        if (loginRequest.getUsername().equals(defaultUsername) && loginRequest.getPassword().equals(defaultPassword)) {
            return true;
        } else {
            //Add database code
            return false;
        }
    }

    // private boolean defaultAuthentication(LoginRequest loginRequest) {
    //     if (loginRequest.getUsername().equals(defaultUsername) && loginRequest.getPassword().equals(defaultPassword)) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    private boolean defaultAuthentication(LoginRequest loginRequest) {
        try {
            String hashedPassword = hashUtil.hashPassword(defaultPassword);
            log.info("[cntrlflow] hashedPasswordDefault: " + hashedPassword);
            log.info("[cntrlflow] hashedPassword: " + loginRequest.getPassword());
            log.info("[cntrlflow] verify password:" + hashUtil.verifyPassword(loginRequest.getPassword(), hashedPassword));
            if (loginRequest.getUsername().equals(defaultUsername)) {
                return hashUtil.verifyPassword(loginRequest.getPassword(), hashedPassword);
            } else {
                return false;
            }
        } catch(NoSuchAlgorithmException | Error e) {
            e.printStackTrace();
            return false;
        }
    }
}