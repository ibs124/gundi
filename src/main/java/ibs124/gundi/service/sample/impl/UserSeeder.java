package ibs124.gundi.service.sample.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ibs124.gundi.model.application.Role;
import ibs124.gundi.model.persistence.AuthorityEntity;
import ibs124.gundi.model.persistence.UserEntity;
import ibs124.gundi.repository.AuthorityRepository;
import ibs124.gundi.repository.UserRepository;

@Component
public class UserSeeder {

    private final String ROOT_EMAIL_PROPERTY = "mail_sample";
    private final String USERNAME_DELIMITER = "_";

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthorityRepository roleRepository;
    private final Environment environment;

    public UserSeeder(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            AuthorityRepository roleRepository,
            Environment environment) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.environment = environment;
    }

    public List<UserEntity> seedUsers() {
        if (this.userRepository.count() > 0) {
            return new ArrayList<>();
        }

        String password = this.passwordEncoder.encode(Config.DEFAULT_USER_PASSWORD);

        List<UserEntity> users = Arrays
                .stream(Config.USER_NAMES)
                .map(x -> this.createByFullNameAndPassword(x, password))
                .toList();

        users = this.createRolesByUsers(users);

        users.get(0).setPrimaryEmail(this.loadRootEmail());

        return this.userRepository.saveAll(users);

    }

    private List<UserEntity> createRolesByUsers(List<UserEntity> users) {
        HashSet<AuthorityEntity> rootRoles = new HashSet<>(
                this.roleRepository.findAll());

        Set<AuthorityEntity> adminRoles = rootRoles
                .stream()
                .filter(x -> x.getName() != Role.ROOT.name())
                .collect(Collectors.toSet());

        Set<AuthorityEntity> userRoles = adminRoles
                .stream()
                .filter(x -> x.getName() != Role.ADMIN.name())
                .collect(Collectors.toSet());

        users.get(0).setAuthorities(rootRoles);

        for (int i = 1; i < users.size(); i++) {
            if (i < Config.ADMINS_COUNT + 1) {
                users.get(i).setAuthorities(adminRoles);
                continue;
            }

            users.get(i).setAuthorities(userRoles);
        }

        return users;
    }

    private UserEntity createByFullNameAndPassword(String fullName, String password) {
        Instant now = Instant.now();
        String username = fullName.toLowerCase().replaceAll(" ", USERNAME_DELIMITER);
        String primaryEmail = username.concat(Config.PRIMARY_EMAIL_SUFFIX);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setPrimaryEmail(primaryEmail);

        user.setLastVerifiedAt(now);
        user.setEnabled(true);
        user.setAccountExpiresAt(
                now.plus(Config.ACCOUNT_EXPIRATION_DAYS, ChronoUnit.DAYS));
        user.setMfaEnabledAt(now);
        return user;
    }

    private String loadRootEmail() {
        return this.environment.getProperty(ROOT_EMAIL_PROPERTY);
    }

}