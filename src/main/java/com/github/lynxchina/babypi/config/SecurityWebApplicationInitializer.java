package com.github.lynxchina.babypi.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author chris
 * @version 1/20/16-5:15 PM
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {


    public SecurityWebApplicationInitializer() {
        super(ServiceConfig.class);
    }
}
