package pl.coderslab.balanceYourDiet.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserEntity mapDtoToEntity(UserDto userDto) {
        return userMapper.mapUserDtoToEntity(userDto);
    }

    public UserEntity mapDtoToEntityNoRelations(UserDto userDto) {
        return userMapper.mapUserDtoToEntityNoRelations(userDto);
    }

    public UserDto mapEntityToDto(UserEntity userEntity) {
        return userMapper.mapUserEntityToDto(userEntity);
    }

    public UserDto mapEntityToDtoNoRelations(UserEntity userEntity) {
        return userMapper.mapUserEntityToDtoNoRelations(userEntity);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> findById(Long userId) {
        return userRepository.findById(userId);
    }

}
