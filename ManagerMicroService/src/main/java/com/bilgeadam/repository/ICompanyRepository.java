package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyRepository extends MongoRepository<Company,String > {
    Optional<Company> findOptionalByCompanyName(String companyName);
    Optional<Company> findById(Long companyId);

}
