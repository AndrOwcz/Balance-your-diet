package pl.coderslab.balanceYourDiet.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.balanceYourDiet.meal.MealDto;
import pl.coderslab.balanceYourDiet.meal.MealEntity;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentEntity mapDtoToEntity(CommentDto commentDto) {
        return commentMapper.mapCommentDtoToEntity(commentDto);
    }

    public CommentDto mapEntityToDto(CommentEntity commentEntity) {
        return commentMapper.mapCommentEntityToDto(commentEntity);
    }

    public List<CommentEntity> mapCommentListDtoToEntity(List<CommentDto> commentDtos) {
        return commentMapper.mapCommentListDtoToEntity(commentDtos);
    }

    public List<CommentDto> mapCommentListEntityToDto(List<CommentEntity> commentEntities) {
        return commentMapper.mapCommentListEntityToDto(commentEntities);
    }

    public List<CommentEntity> findAllById(Long id) {
        return commentRepository.findAllById(id);
    }

    public List<CommentEntity> findAllComments() {
        return commentRepository.findAll();
    }

    public List<CommentEntity> findAllByUserId(Long id) {
        return commentRepository.findAllByUserEntityId(id);
    }

    public Optional<CommentEntity> findById(Long id) {
        return commentRepository.findById(id);
    }

    public CommentEntity save(CommentEntity commentEntity) {
        return commentRepository.save(commentEntity);
    }

    public List<CommentEntity> findAll() {
        return commentRepository.findAll();
    }
}
