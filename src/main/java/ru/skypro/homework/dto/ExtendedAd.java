package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAd {

    private Integer pk;
    private String authorFirstNam;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;
}