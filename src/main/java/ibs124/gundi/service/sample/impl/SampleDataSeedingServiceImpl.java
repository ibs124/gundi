package ibs124.gundi.service.sample.impl;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ibs124.gundi.service.sample.SampleDataSeedingService;
import jakarta.transaction.Transactional;

@Service
class SampleDataSeedingServiceImpl implements SampleDataSeedingService {

    private static final String HIBERNATE_DDL = "spring.jpa.hibernate.ddl-auto";
    private static final String HIBERNATRE_DDL_CREATE_DROP = "create-drop";

    private final UserSeeder userSeeder;
    private final Environment env;

    public SampleDataSeedingServiceImpl(UserSeeder userSeeder, Environment env) {
        this.userSeeder = userSeeder;
        this.env = env;
    }

    @Override
    @Transactional
    public void seedSampleData() {
        if (!this.isSeedingEnabled()) {
            return;
        }

        this.userSeeder.seedUsers();
    }

    private boolean isSeedingEnabled() {
        String hibernateDdlAuto = this.env.getProperty(HIBERNATE_DDL);

        return hibernateDdlAuto.equals(HIBERNATRE_DDL_CREATE_DROP);
    }

}
