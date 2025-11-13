package ibs124.gundi.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ibs124.gundi.exception.ResourceCreatingException;
import ibs124.gundi.mapper.UserMapper;
import ibs124.gundi.model.domain.User;
import ibs124.gundi.model.domain.UserRole;
import ibs124.gundi.model.dto.RegisterDto;
import ibs124.gundi.model.dto.RegisterResponseDto;
import ibs124.gundi.model.dto.UserDto;
import ibs124.gundi.model.enumm.UserRoleType;
import ibs124.gundi.repository.UserRepository;
import ibs124.gundi.repository.UserRoleRepository;

@Service
class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(UserRepository userRepository, UserRoleRepository roleRepository,
            UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponseDto register(RegisterDto request) {
        try {
            User user = this.userMapper.toDomainModel(request);

            UserRole userRole = this.roleRepository
                    .getReferenceById(UserRoleType.USER.ordinal() + 1L);

            user.addRole(userRole);

            user.setPassword(
                    this.passwordEncoder
                            .encode(user.getPassword()));

            user = this.userRepository.save(user);

            UserDto result = this.userMapper.toServiceModel(user);

            return new RegisterResponseDto(result);
        } catch (Exception e) {
            throw new ResourceCreatingException(e);
        }
    }
}