package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collection;
import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense,String> {

    List<Expense> findAllByUserId(Long userId);
}
