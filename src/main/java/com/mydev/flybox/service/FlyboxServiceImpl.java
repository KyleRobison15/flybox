package com.mydev.flybox.service;

import com.mydev.flybox.errors.BadRequestException;
import com.mydev.flybox.model.Fly;
import com.mydev.flybox.model.User;
import com.mydev.flybox.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

@Slf4j
@Service
public class FlyboxServiceImpl implements FlyboxService {

    @Autowired
    UserRepository userRepo;

    @Override
    public List<Fly> getUserFlybox(String username) {
        StopWatch sw = new StopWatch();
        sw.start("Get flybox for user: " + username);

        User user = userRepo.findByUsername(username);

        sw.stop();
        log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");

        return user.getFlies();
    }

    @Override
    public Fly getFlyFromFlybox(String username, String flyName) {
        StopWatch sw = new StopWatch();
        sw.start("Get Fly From Flybox");

        User user = userRepo.findByUsername(username);
        List<Fly> userFlybox = user.getFlies();

        for (Fly fly : userFlybox) {
            if (fly.getName().equals(flyName)) {
                sw.stop();
                log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");
                return fly;
            }
        }

        sw.stop();
        log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");
        return null;
    }

    @Override
    public Fly addFlyToFlybox(String username, Fly fly) throws BadRequestException {
        StopWatch sw = new StopWatch();
        sw.start("Add Fly to Flybox.");

        User user = userRepo.findByUsername(username);
        List<Fly> userFlybox = user.getFlies();

        // Check for duplicate fly name in user's fly box
        // User's may not add the same fly twice in their flybox
        for(Fly userFly : userFlybox) {
            if (userFly.getName().equals(fly.getName())){
                throw new BadRequestException("You already have this fly in your flybox.");
            }
        }

        if (fly == null){
            return null;
        } else {
            userFlybox.add(fly);
            userRepo.save(user);
        }

        sw.stop();
        log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");

        return fly;

    }

    @Override
    public Fly updateFlyInFlybox(String username, String flyName, Fly fly) {

        StopWatch sw = new StopWatch();
        sw.start("Update a fly in the flybox.");

        User user = userRepo.findByUsername(username);
        List<Fly> flybox = user.getFlies();

        for(Fly originalFly : flybox){
            if (originalFly.getName().equals(flyName)){
                flybox.set(flybox.indexOf(originalFly), fly);
                userRepo.save(user);

                sw.stop();
                log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");

                return flybox.get(flybox.indexOf(fly));
            }
        }

        sw.stop();
        log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");
        return null;
    }

    @Override
    public boolean deleteFlyFromFlybox(String username, String flyName) {

        StopWatch sw = new StopWatch();
        sw.start("Delete fly from flybox");

        User user = userRepo.findByUsername(username);
        List<Fly> userFlybox = user.getFlies();

//        userFlybox.remove(2);
//        userRepo.save(user);

        for(Fly fly : userFlybox){
            if (fly.getName().equals(flyName)){
                userFlybox.remove(fly);
                userRepo.save(user);

                sw.stop();
                log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");

                return true;
            }
        }

        sw.stop();
        log.debug(sw.shortSummary() + " (" + sw.getLastTaskTimeMillis() +" ms)");
        return false;
    }
}
