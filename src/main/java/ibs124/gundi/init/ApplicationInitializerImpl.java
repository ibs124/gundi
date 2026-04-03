package ibs124.gundi.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ibs124.gundi.service.auth.user.AuthorityCreatingService;
import ibs124.gundi.service.sample.SampleDataSeedingService;

@Component
class ApplicationInitializerImpl implements CommandLineRunner, ApplicationInitializer {

    private final AuthorityCreatingService authorityCreatingService;
    private final SampleDataSeedingService sampleDataSeedingService;

    public ApplicationInitializerImpl(
            AuthorityCreatingService authorityInitService,
            SampleDataSeedingService sampleDataSeedingService) {
        this.authorityCreatingService = authorityInitService;
        this.sampleDataSeedingService = sampleDataSeedingService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initializeApplication();
    }

    @Override
    public void initializeApplication() {
        this.authorityCreatingService.create();

        this.sampleDataSeedingService.seedSampleData();
    }

}
