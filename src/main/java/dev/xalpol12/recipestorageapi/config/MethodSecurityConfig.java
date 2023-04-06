package dev.xalpol12.recipestorageapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //TODO: deprecated
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
