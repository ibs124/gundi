package ibs124.gundi.initialization;

import static ibs124.gundi.constant.Formats.LOGGING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ibs124.gundi.configuration.PropertyConfig;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.service.auth.RoleInitializingService;
import ibs124.gundi.service.test.TestingContextInitializingService;
import ibs124.gundi.utility.Application;

@Component
class AppInitializer implements CommandLineRunner {

    private final PropertyConfig propertyConfig;
    private final RoleInitializingService roleInitService;
    private final TestingContextInitializingService testingContextInitializingService;
    private final Logger logger;

    public AppInitializer(
            PropertyConfig propertyConfig,
            RoleInitializingService roleInitService,
            TestingContextInitializingService dataSeedingService) {
        this.propertyConfig = propertyConfig;
        this.roleInitService = roleInitService;
        this.testingContextInitializingService = dataSeedingService;

        this.logger = LoggerFactory.getLogger(AppInitializer.class);
    }

    @Override
    public void run(String... args) {
        this.logger.info(LOGGING, this.roleInitService.initializeRoles());

        this.testingContextInitializingService.initializeTestingContext();

        this.logger.info(LOGGING, propertyConfig);

        this.logger.info(LOGGING, Application.mapConstants(Routes.class));
    }

}
