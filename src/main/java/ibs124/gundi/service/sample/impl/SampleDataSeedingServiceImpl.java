package ibs124.gundi.service.sample.impl;

import org.springframework.stereotype.Service;

import ibs124.gundi.service.sample.SampleDataSeedingService;
import jakarta.transaction.Transactional;

@Service
class SampleDataSeedingServiceImpl implements SampleDataSeedingService {

    private final UserSeeder userSeeder;

    public SampleDataSeedingServiceImpl(UserSeeder userSeeder) {
        this.userSeeder = userSeeder;
    }

    @Override
    @Transactional
    public void seedSampleData() {
        this.userSeeder.seedUsers();
    }

}
