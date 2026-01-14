package ibs124.gundi.service.auth.component;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.UserRepository;

@Service
class UserCreatorImpl implements UserCreator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCreatorImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(User user) {
        String encodedPassword = this.passwordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        user = this.userRepository.save(user);

        return user;
    }

}