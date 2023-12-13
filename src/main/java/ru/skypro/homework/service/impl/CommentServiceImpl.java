package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public Comments getCommentsAds(Integer id) {
        return null;
    }

    @Override
    public Comment setCommentAds(Integer id, CreateOrUpdateComment comment) {
        return null;
    }

    @Override
    public void removeComment(Integer adId, Integer commentId) {

    }

    @Override
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        return null;
    }
}
