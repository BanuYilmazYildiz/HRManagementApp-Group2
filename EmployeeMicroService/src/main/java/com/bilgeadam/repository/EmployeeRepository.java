package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

    Optional<Employee> findOptionalByUserId(Long userId);


    List<Employee> findAllByCompany(String company);
}
