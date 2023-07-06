package com.stock.AlphaVantageAPI.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix = "alphavantage.api.key")

public class AlphaVantageConfig {

    private String apiKey;

  
}