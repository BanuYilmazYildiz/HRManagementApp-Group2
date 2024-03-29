package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PermissionRepository extends MongoRepository<Permission,String> {


    List<Permission> findAllByUserId(Long userId);
}
