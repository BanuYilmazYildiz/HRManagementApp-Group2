package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Advance;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAdvanceRepository extends MongoRepository<Advance,String> {
}
