package com.manu.multiple_database.all_database.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepo extends MongoRepository<SecondUser,Integer> {
}
