package com.mydev.flybox.controller;

import com.mydev.flybox.errors.BadRequestException;
import com.mydev.flybox.model.Fly;
import com.mydev.flybox.service.FlyboxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:3000/*"})
public class FlyboxController {

    @Autowired
    FlyboxService flyboxSvc;

    @GetMapping("flybox/{flyName}")
    public Fly getFlyFromFlybox(@PathVariable String flyName, Principal principal) throws BadRequestException {
        Fly fly = flyboxSvc.getFlyFromFlybox(principal.getName(), flyName);

        if (fly == null) {
            throw new BadRequestException("Fly not found.");
        }

        return fly;

    }

    @GetMapping("flybox")
    public List<Fly> getFlyFromFlybox(Principal principal) throws BadRequestException {

        List<Fly> flyBox = flyboxSvc.getUserFlybox(principal.getName());

        if (flyBox == null) {
            throw new BadRequestException("No flybox found.");
        } else if (flyBox.isEmpty()) {
            throw new BadRequestException("You have no flies in your flybox.");
        }

        return flyBox;

    }

    @PostMapping("flybox")
    public Fly addFlyToFlybox(@RequestBody Fly fly, Principal principal) throws BadRequestException {

        if (fly == null) {
            throw new BadRequestException("This fly cannot be added to your flybox.");
        } else {
            return flyboxSvc.addFlyToFlybox(principal.getName(), fly);
        }
    }

    @PutMapping("flybox/{flyName}")
    public Fly updateFlyInFlybox(@PathVariable String flyName, Principal principal, @RequestBody Fly fly) throws BadRequestException {

        if (fly == null){
            throw new BadRequestException("Update failed. No fly with the name " + flyName + " was found.");
        } else {
            return flyboxSvc.updateFlyInFlybox(principal.getName(), flyName, fly);
        }
    }

    @DeleteMapping("flybox/{flyName}")
    public boolean deleteFlyFromFlybox(@PathVariable String flyName, Principal principal) throws BadRequestException {

        boolean isDeleted = flyboxSvc.deleteFlyFromFlybox(principal.getName(), flyName);

        if (!isDeleted){
            throw new BadRequestException("Delete failed. No fly with the name " + flyName + " was found.");
        }

        return true;
    }


}
