package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Advance;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface IAdvanceRepository extends MongoRepository<Advance,String> {
    List<Advance> findAllByUserId(Long userId);
}
