package com.mydev.flybox.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Fly {

    @Id
    private String name;

    private String description;

    private String lifecycle;

    private String sizeRange;

    private String favoriteSize;

    private Category category;

    private Recipe recipe;

    private Boolean tick;

    private String tyingNotes;

    private String imageUrl;

    private String videoUrl;

}
