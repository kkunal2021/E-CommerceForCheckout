package com.kunal.ecommerce.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kunal
 * @project ECommerceCheckout
 */

@Configuration
@EnableTransactionManagement
@ComponentScan (basePackages = "com.kunal.ecommerce.internal.repository")
@PropertySource("classpath:application.properties")
public class JpaConfig {
}
