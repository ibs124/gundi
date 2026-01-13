package ibs124.gundi.service.auth.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.application.UserCreateDTO;
import ibs124.gundi.model.application.UserDTO;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.repository.UserRepository;

@Service
class UserCreateDomainServiceImpl implements UserCreateDomainService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserCreateDomainServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO create(UserCreateDTO dto) {
        User user = this.userMapper.mapToDomainModel(dto);

        user.setPassword(this.passwordEncoder
                .encode(dto.password()));

        user = this.userRepository.save(user);

        return this.userMapper.mapToApplicationModel(user);
    }

}