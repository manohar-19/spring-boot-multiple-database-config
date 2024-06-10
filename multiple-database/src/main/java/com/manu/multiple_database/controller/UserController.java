package com.manu.multiple_database.controller;

import com.manu.multiple_database.all_database.first_database.first_repository.FirstUserRepo;
import com.manu.multiple_database.all_database.first_database.first_model.FirstUser;
import com.manu.multiple_database.all_database.mongodb.MongoUserRepo;
import com.manu.multiple_database.all_database.second_database.second_model.SecondUser;
import com.manu.multiple_database.all_database.second_database.second_repository.SecondUserRepo;
import com.manu.multiple_database.all_database.third_database.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    FirstUserRepo firstUserRepo;

    @Autowired
    SecondUserRepo secondUserRepo;

    @Autowired
    MongoUserRepo mongoUserRepo;

    @Autowired
    QueryService queryService;

    @GetMapping("/getFirst")
    @Cacheable(cacheNames = "users")
    public List<FirstUser> getUserByFirstDB() throws InterruptedException {
        Thread.sleep(3000);
        return firstUserRepo.findAll();
    }

    @GetMapping("/getFirstById/{id}")
    @Cacheable(cacheNames = "user", key = "#id")
    public Optional<FirstUser> getUserByFirstDbById(@PathVariable("id") Integer id) {
        return firstUserRepo.findById(id);
    }

    @GetMapping("/getSecond")
    public List<SecondUser> getUserBySecondDB() {
        return secondUserRepo.findAll();
    }

    @GetMapping("/getMongo")
    public List<com.manu.multiple_database.all_database.mongodb.SecondUser> getUserByMongoDB() {
        return mongoUserRepo.findAll();
    }

    @GetMapping("/getThird")
    public List<SecondUser> getUserByThirdDB() {
        return queryService.executeQuery();
    }
}
