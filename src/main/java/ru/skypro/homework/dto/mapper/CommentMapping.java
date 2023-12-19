package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

@Service
public class CommentMapping {
    public CommentDto mapToCommentDto(Comment entity) {
        CommentDto dto = new CommentDto();

        dto.setAuthor(entity.getUser().getId());
        dto.setAuthorImage(entity.getUser().getImage());
        dto.setAuthorFirstName(entity.getUser().getFirstName());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPk(entity.getPk());
        dto.setText(entity.getText());
        return dto;
    }

    public Comment mapToComment(CommentDto dto) {
        Comment entity = new Comment();
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setPk(dto.getPk());
        entity.setText(dto.getText());
        return entity;
    }
}
