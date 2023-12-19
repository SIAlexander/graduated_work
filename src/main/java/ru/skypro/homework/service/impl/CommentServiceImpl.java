package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public CommentsDto getCommentsAds(Integer id) {
        return null;
    }

    @Override
    public CommentDto setCommentAds(Integer id, CreateOrUpdateCommentDto comment) {
        return null;
    }

    @Override
    public void removeComment(Integer adId, Integer commentId) {

    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto comment) {
        return null;
    }
}
