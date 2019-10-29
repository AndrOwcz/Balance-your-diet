package pl.coderslab.balanceYourDiet.user;

import org.springframework.stereotype.Component;

@Component
public final class UserMapper {

    public UserEntity mapUserDtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setRequiredCalories(userDto.getRequiredCalories());
        userEntity.setRequiredCarbs(userDto.getRequiredCarbs());
        userEntity.setRequiredFats(userDto.getRequiredFats());
        userEntity.setRequiredProtein(userDto.getRequiredProtein());
        return userEntity;
    }

    public UserDto mapUserEntityToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setUsername(userEntity.getUsername());
        userDto.setRequiredCalories(userEntity.getRequiredCalories());
        userDto.setRequiredCarbs(userEntity.getRequiredCarbs());
        userDto.setRequiredFats(userEntity.getRequiredFats());
        userDto.setRequiredProtein(userEntity.getRequiredProtein());
        return userDto;
    }
}
