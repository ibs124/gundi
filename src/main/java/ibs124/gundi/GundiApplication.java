package ibs124.gundi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import ibs124.gundi.configuration.PropertyConfig;

@SpringBootApplication
@EnableConfigurationProperties(PropertyConfig.class)
@EnableJpaAuditing
public class GundiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GundiApplication.class, args);
    }
}
