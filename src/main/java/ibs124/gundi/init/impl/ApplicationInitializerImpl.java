package ibs124.gundi.init.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ibs124.gundi.init.ApplicationInitializer;
import ibs124.gundi.service.auth.AuthorityInitializingService;
import ibs124.gundi.service.sample.SampleDataSeedingService;

@Component
class ApplicationInitializerImpl implements CommandLineRunner, ApplicationInitializer {

    private final AuthorityInitializingService authorityInitService;
    private final SampleDataSeedingService sampleDataSeedingService;

    public ApplicationInitializerImpl(
            AuthorityInitializingService authorityInitService,
            SampleDataSeedingService sampleDataSeedingService) {
        this.authorityInitService = authorityInitService;
        this.sampleDataSeedingService = sampleDataSeedingService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initializeApplication();
    }

    @Override
    public void initializeApplication() {
        this.authorityInitService.initialize();

        this.sampleDataSeedingService.seedSampleData();
    }

}
