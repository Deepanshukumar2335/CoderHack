package com.crio.CoderHack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.CoderHack.model.User;

public interface UserRepository extends MongoRepository<User, String>{
    
}
