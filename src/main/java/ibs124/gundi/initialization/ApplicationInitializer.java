package ibs124.gundi.initialization;

import static ibs124.gundi.constant.Formats.LOGGING;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ibs124.gundi.configuration.PropertyConfiguration;
import ibs124.gundi.constant.Routes;
import ibs124.gundi.service.auth.RoleInitializingService;
import ibs124.gundi.utility.AppUtils;

@Component
@Order(1)
class ApplicationInitializer implements CommandLineRunner {

    private final PropertyConfiguration propertyConfig;
    private final RoleInitializingService roleInitService;
    private final Logger logger;

    public ApplicationInitializer(
            PropertyConfiguration propertyConfig,
            RoleInitializingService roleInitService) {
        this.propertyConfig = propertyConfig;
        this.roleInitService = roleInitService;
        this.logger = LoggerFactory.getLogger(ApplicationInitializer.class);
    }

    @Override
    public void run(String... args) {
        this.logger.info(LOGGING, this.roleInitService.initializeRoles());

        this.logger.info(LOGGING, propertyConfig);

        this.logger.info(LOGGING, AppUtils.mapConstants(Routes.class));
    }

}
