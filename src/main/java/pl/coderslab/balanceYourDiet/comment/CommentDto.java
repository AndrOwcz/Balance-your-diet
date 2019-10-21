package pl.coderslab.balanceYourDiet.comment;

import pl.coderslab.balanceYourDiet.user.UserDto;

public class CommentDto {

    private Long id;

    private String content;

    private UserDto userDto;

    public CommentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
