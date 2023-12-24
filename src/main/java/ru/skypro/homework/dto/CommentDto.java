package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Integer pk;
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Integer createdAt;
    private String text;
}
