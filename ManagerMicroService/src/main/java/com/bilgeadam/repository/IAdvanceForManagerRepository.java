package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.AdvanceForManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdvanceForManagerRepository extends MongoRepository<AdvanceForManager,String> {
}
