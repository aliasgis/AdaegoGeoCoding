package com.jygis.geocoding;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jygis")
public class PropertiesConfig {
    private String geoFilePath;
    private String geoFileName;
}
