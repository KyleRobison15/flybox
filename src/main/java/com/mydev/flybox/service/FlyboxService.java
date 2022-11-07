package com.mydev.flybox.service;

import com.mydev.flybox.errors.BadRequestException;
import com.mydev.flybox.model.Fly;

import java.util.List;

public interface FlyboxService {

    List<Fly> getUserFlybox(String username);
    Fly getFlyFromFlybox(String username, String flyName);
    Fly addFlyToFlybox(String username, Fly fly) throws BadRequestException;
    Fly updateFlyInFlybox(String username, String flyName, Fly fly);
    boolean deleteFlyFromFlybox(String username, String flyName);

}
