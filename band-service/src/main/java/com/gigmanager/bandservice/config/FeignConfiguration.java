package com.gigmanager.bandservice.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.gigmanager.bandservice")
public class FeignConfiguration {

}
