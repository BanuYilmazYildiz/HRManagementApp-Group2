package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.ExpenseForManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseForManagerRepository extends MongoRepository<ExpenseForManager,String> {
}
