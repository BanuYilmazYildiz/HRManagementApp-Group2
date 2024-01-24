package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String> {

}
