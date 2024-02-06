package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.ExpenseForManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IExpenseForManagerRepository extends MongoRepository<ExpenseForManager,String> {
    List<ExpenseForManager> findAllByCompany(String company);

    Optional<ExpenseForManager> findByExpenseId(String expenseId);
}
