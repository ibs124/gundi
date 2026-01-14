package ibs124.gundi.service.seed.component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ibs124.gundi.config.SeedConfig;
import ibs124.gundi.model.domain.Role;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.enumm.RoleName;
import ibs124.gundi.repository.RoleRepository;
import ibs124.gundi.repository.UserRepository;

@Component
public class UserSeeder {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserSeeder(
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> seedUsers() {
        String password = this.passwordEncoder.encode(SeedConfig.DEFAULT_USER_PASSWORD);

        List<User> users = Arrays
                .stream(SeedConfig.USER_NAMES)
                .map(x -> this.createByFullNameAndPassword(x, password))
                .toList();

        users = this.createRolesByUsers(users);

        return this.userRepository.saveAll(users);

    }

    private List<User> createRolesByUsers(List<User> users) {
        HashSet<Role> rootRoles = new HashSet<>(
                this.roleRepository.findAll());

        Set<Role> adminRoles = rootRoles
                .stream()
                .filter(x -> x.getName() != RoleName.ROOT)
                .collect(Collectors.toSet());

        Set<Role> userRoles = adminRoles
                .stream()
                .filter(x -> x.getName() != RoleName.ADMIN)
                .collect(Collectors.toSet());

        users.get(0).setRoles(rootRoles);

        for (int i = 1; i < users.size(); i++) {
            if (i < SeedConfig.ADMINS_COUNT + 1) {
                users.get(i).setRoles(adminRoles);
                continue;
            }

            users.get(i).setRoles(userRoles);
        }

        return users;
    }

    private User createByFullNameAndPassword(String fullName, String password) {
        String username = fullName.toLowerCase().replaceAll(" ", "_");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setEnabled(true);
        return user;
    }

}