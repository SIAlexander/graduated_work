package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.mapper.CommentMapping;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapping commentMapping;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, AdRepository adRepository, CommentMapping commentMapping, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.adRepository = adRepository;
        this.commentMapping = commentMapping;
        this.userRepository = userRepository;
    }

    /**
     * Метод получения комментариев объявления
     *
     * @param id
     * @return CommentsDto
     */
    @Override
    public CommentsDto getCommentsAds(Integer id) {
        List<Comment> comment = commentRepository.findByAdPk(id);
        if (comment == null) {
            return null;
        }
        List<CommentDto> commentList = comment.stream()
                .map(commentMapping::mapToCommentDto)
                .collect(Collectors.toList());
        return new CommentsDto(commentList.size(), commentList);
    }

    /**
     * Метод добавления комментария к объявлению
     *
     * @param id
     * @param createCommentDto
     * @return CommentDto
     */
    @Override
    public CommentDto setCommentAds(Integer id, CreateOrUpdateCommentDto createCommentDto, Authentication authentication) {
        Timestamp localDateTime = Timestamp.valueOf(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        Comment comment = new Comment();
        Ad ad = adRepository.findByPk(id);
        if (ad == null) {
            return null;
        }
        comment.setText(createCommentDto.getText());
        comment.setCreatedAt(localDateTime);
        comment.setAd(ad);
        comment.setUser(userRepository.findByUserName(authentication.getName()));
        commentRepository.save(comment);
        return commentMapping.mapToCommentDto(comment);
    }

    /**
     * Метод удаления комментария
     *
     * @param adId
     * @param commentId
     */
    @Override
    public void removeComment(Integer adId, Integer commentId) {
        Comment comment = commentRepository.findByPk(commentId);
        commentRepository.delete(comment);
    }

    /**
     * Метод обновления комментария
     *
     * @param adId
     * @param commentId
     * @param createCommentDto
     * @return CommentDto
     */
    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createCommentDto) {
        Comment comment = commentRepository.findByPk(commentId);
        if (comment == null) {
            return null;
        }
        comment.setText(createCommentDto.getText());
        commentRepository.save(comment);
        return commentMapping.mapToCommentDto(comment);
    }
}
