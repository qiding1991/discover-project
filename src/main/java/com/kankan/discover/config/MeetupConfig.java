package com.kankan.discover.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@ConfigurationProperties(prefix = "meetup")
public class MeetupConfig {
  private String clientId;
  private String clientSecret;
  private String redirectUri;
}
