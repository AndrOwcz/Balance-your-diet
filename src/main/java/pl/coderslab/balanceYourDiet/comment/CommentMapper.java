package pl.coderslab.balanceYourDiet.comment;

import org.springframework.stereotype.Component;
import pl.coderslab.balanceYourDiet.user.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public final class CommentMapper {

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CommentEntity mapCommentDtoToEntity(CommentDto commentDto) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentDto.getContent());
        commentEntity.setUserEntity(userMapper.mapUserDtoToEntity(commentDto.getUserDto()));
        return commentEntity;
    }

    public CommentDto mapCommentEntityToDto(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setContent(commentEntity.getContent());
        commentDto.setUserDto(userMapper.mapUserEntityToDto(commentEntity.getUserEntity()));
        return commentDto;
    }

    public List<CommentEntity> mapCommentListDtoToEntity(List<CommentDto> commentDtos) {
        return commentDtos.stream().map(this::mapCommentDtoToEntity).collect(Collectors.toList());
    }

    public List<CommentDto> mapCommentListEntityToDto(List<CommentEntity> commentEntities) {
        return commentEntities.stream().map(this::mapCommentEntityToDto).collect(Collectors.toList());
    }
}
