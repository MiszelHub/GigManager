package com.gigmanager.gigservice.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.gigmanager.gigservice")
public class FeignConfiguration {

}
