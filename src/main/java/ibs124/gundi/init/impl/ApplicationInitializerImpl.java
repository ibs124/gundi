package ibs124.gundi.init.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ibs124.gundi.init.ApplicationInitializer;
import ibs124.gundi.service.auth.AuthorityInitService;
import ibs124.gundi.service.sample.SampleDataSeedingService;

@Component
class ApplicationInitializerImpl implements CommandLineRunner, ApplicationInitializer {

    private final AuthorityInitService roleInitializingService;
    private final SampleDataSeedingService sampleDataSeedingService;

    public ApplicationInitializerImpl(
            AuthorityInitService roleInitializingService,
            SampleDataSeedingService sampleDataSeedingService) {
        this.roleInitializingService = roleInitializingService;
        this.sampleDataSeedingService = sampleDataSeedingService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initializeApplication();
    }

    @Override
    public void initializeApplication() {
        this.roleInitializingService.init();

        this.sampleDataSeedingService.seedSampleData();
    }

}
