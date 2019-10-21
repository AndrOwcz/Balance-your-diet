package pl.coderslab.balanceYourDiet.user;

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserEntity mapDtoToEntity (UserDto userDto) {
        return userMapper.mapUserDtoToEntity(userDto);
    }
    public UserDto mapEntityToDto (UserEntity userEntity) {
        return userMapper.mapUserEntityToDto(userEntity);
    }



}
