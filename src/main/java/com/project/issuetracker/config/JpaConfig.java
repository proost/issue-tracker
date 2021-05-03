package com.project.issuetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Profile({"dev", "prod"})
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
