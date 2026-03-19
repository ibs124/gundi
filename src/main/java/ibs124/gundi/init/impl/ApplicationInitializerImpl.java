package ibs124.gundi.init.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ibs124.gundi.init.ApplicationInitializer;
import ibs124.gundi.model.application.Authority;
import ibs124.gundi.model.application.Role;
import ibs124.gundi.service.auth.AuthorityCreatingService;
import ibs124.gundi.service.sample.SampleDataSeedingService;

@Component
class ApplicationInitializerImpl implements CommandLineRunner, ApplicationInitializer {

    private final AuthorityCreatingService authorityCreatingService;
    private final SampleDataSeedingService sampleDataSeedingService;

    public ApplicationInitializerImpl(
            AuthorityCreatingService roleInitializingService,
            SampleDataSeedingService sampleDataSeedingService) {
        this.authorityCreatingService = roleInitializingService;
        this.sampleDataSeedingService = sampleDataSeedingService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initializeApplication();
    }

    @Override
    public void initializeApplication() {
        this.authorityCreatingService
                .create(this.loadDefaultAuthorities());

        this.sampleDataSeedingService.seedSampleData();
    }

    private Collection<? extends Authority> loadDefaultAuthorities() {
        return Arrays.asList(Role.values());
    }

}
