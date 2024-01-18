package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findOptionalByEmailAndPassword(String email, String password);
}
