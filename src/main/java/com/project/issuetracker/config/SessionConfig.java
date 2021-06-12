package com.project.issuetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Profile({"prod", "dev"})
@EnableRedisHttpSession
@Configuration
public class SessionConfig {
}
