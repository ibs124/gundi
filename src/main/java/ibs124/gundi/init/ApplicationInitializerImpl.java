package ibs124.gundi.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.service.user.UserRoleService;

@Component
class ApplicationInitializerImpl implements ApplicationInitializer, CommandLineRunner {

    private final UserRoleService roleService;

    public ApplicationInitializerImpl(UserRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.initializeApplication();
    }

    @Override
    public void initializeApplication() {
        try {
            this.roleService.initializeUserRoles();
        } catch (Exception e) {
            throw new ResourceCreatingException(e);
        }
    }
}