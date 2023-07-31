package com.example.wantedpreonboardingbackend.model.repository;

import com.example.wantedpreonboardingbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
