package com.mydev.flybox.model;

import lombok.Data;

import java.util.List;

@Data
public class Fly {

    private String name;

    private String description;

    private String lifecycle;

    private String sizeRange;

    private String favoriteSize;

    private Category category;

    private Recipe recipe;

    private Boolean tick;

    private List<String> tyingNotes;

    private String stockImageUrl;

    private String videoUrl;

    private Double retailPrice;

}
