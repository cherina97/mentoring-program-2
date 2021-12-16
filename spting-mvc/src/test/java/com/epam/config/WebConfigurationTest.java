package com.epam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan({"com.epam.dao.impl", "com.epam.storage", "com.epam.service"})
@PropertySource("classpath:app.properties")
@Configuration
public class WebConfigurationTest {

}
