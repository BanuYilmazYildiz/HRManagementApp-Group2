package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.AdvanceForManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAdvanceForManagerRepository extends MongoRepository<AdvanceForManager,String> {
    Optional<AdvanceForManager> findByAdvanceId(String advanceId);

    List<AdvanceForManager> findAllByCompany(String company);
}
