package com.mydev.flybox.model;

import lombok.Data;

import java.util.List;

@Data
public class Recipe {

    private Hook hook;

    private String thread;

    private List<String> materialsList;

}
