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

import ibs124.gundi.model.domain.Authority;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.enumm.Role;
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

    public List<User> seedUsers() {
        if (this.userRepository.count() > 0) {
            return new ArrayList<>();
        }

        String password = this.passwordEncoder.encode(Config.DEFAULT_USER_PASSWORD);

        List<User> users = Arrays
                .stream(Config.USER_NAMES)
                .map(x -> this.createByFullNameAndPassword(x, password))
                .toList();

        users = this.createRolesByUsers(users);

        users.get(0).setPrimaryEmail(this.loadRootEmail());

        return this.userRepository.saveAll(users);

    }

    private List<User> createRolesByUsers(List<User> users) {
        HashSet<Authority> rootRoles = new HashSet<>(
                this.roleRepository.findAll());

        Set<Authority> adminRoles = rootRoles
                .stream()
                .filter(x -> x.getName() != Role.ROOT)
                .collect(Collectors.toSet());

        Set<Authority> userRoles = adminRoles
                .stream()
                .filter(x -> x.getName() != Role.ADMIN)
                .collect(Collectors.toSet());

        users.get(0).setRoles(rootRoles);

        for (int i = 1; i < users.size(); i++) {
            if (i < Config.ADMINS_COUNT + 1) {
                users.get(i).setRoles(adminRoles);
                continue;
            }

            users.get(i).setRoles(userRoles);
        }

        return users;
    }

    private User createByFullNameAndPassword(String fullName, String password) {
        Instant now = Instant.now();
        String username = fullName.toLowerCase().replaceAll(" ", USERNAME_DELIMITER);
        String primaryEmail = username.concat(Config.PRIMARY_EMAIL_SUFFIX);

        User user = new User();
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