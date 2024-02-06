package com.bilgeadam.repository;


import com.bilgeadam.repository.entity.PermissionForManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPermissionForManagerRepository extends MongoRepository<PermissionForManager,String> {
    List<PermissionForManager> findAllByCompany(String company);

    Optional<PermissionForManager> findByPermissionId(String permissionId);
}
