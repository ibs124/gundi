package ibs124.gundi.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ibs124.gundi.config.PropertyConfig;
import ibs124.gundi.config.RouteConfig;
import ibs124.gundi.service.application.seed.DataSeedingService;
import ibs124.gundi.service.auth.RoleInitService;
import ibs124.gundi.util.AppUtils;

@Component
class AppInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    private final PropertyConfig propertyConfig;
    private final RoleInitService roleInitService;
    private final DataSeedingService dataSeedingService;

    public AppInitializer(
            PropertyConfig propertyConfig,
            RoleInitService roleInitService,
            DataSeedingService dataSeedingService) {
        this.propertyConfig = propertyConfig;
        this.roleInitService = roleInitService;
        this.dataSeedingService = dataSeedingService;
    }

    @Override
    public void run(String... args) {
        log.info("{}", this.roleInitService.init());

        this.dataSeedingService.seedTestData();

        log.info("{}", propertyConfig);

        log.info("{}", AppUtils.mapConstants(RouteConfig.class));
    }

}
