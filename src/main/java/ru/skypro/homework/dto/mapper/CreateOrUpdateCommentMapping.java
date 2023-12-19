package ru.skypro.homework.dto.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;

@Service
public class CreateOrUpdateCommentMapping {
    public CreateOrUpdateCommentDto mapToCreateOrUpdateCommentDto(Comment entity) {
        CreateOrUpdateCommentDto dto = new CreateOrUpdateCommentDto();
        dto.setText(entity.getText());
        return dto;
    }

    public Comment mapToComment(CreateOrUpdateCommentDto dto) {
        Comment entity = new Comment();
        entity.setText(dto.getText());
        return entity;
    }
}
