package com.bilgeadam.repository;


import com.bilgeadam.repository.entity.PermissionForManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermissionForManagerRepository extends MongoRepository<PermissionForManager,String> {
}
