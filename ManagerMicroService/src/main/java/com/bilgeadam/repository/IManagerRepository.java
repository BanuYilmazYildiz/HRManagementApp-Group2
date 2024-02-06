package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Manager;
import com.bilgeadam.utility.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IManagerRepository extends MongoRepository<Manager,String> {
    Optional<Manager> findOptionalByUserId(Long userId);

    List<Manager> findAllByRole(ERole eRole);
}
