package org.tarmanc.ecommerce.customerservice.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@EnableDiscoveryClient
@Configuration
public class LocalDiscoveryConfig {
}
