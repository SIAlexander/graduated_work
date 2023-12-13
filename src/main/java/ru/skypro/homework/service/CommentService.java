package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {
    Comments getCommentsAds(Integer id);

    Comment setCommentAds(Integer id, CreateOrUpdateComment comment);

    void removeComment(Integer adId, Integer commentId);

    Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment comment);
}
