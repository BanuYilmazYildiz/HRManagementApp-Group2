package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {

    Optional<UserProfile> findOptionalByEmailAndPassword(String email, String password);

    Optional<UserProfile> findOptionalByPersonalEmail(String personalEmail);
}
