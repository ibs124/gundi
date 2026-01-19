package ibs124.gundi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import ibs124.gundi.configuration.PropertyConfig;

@SpringBootApplication
@EnableConfigurationProperties(PropertyConfig.class)
public class GundiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GundiApplication.class, args);
    }
}
