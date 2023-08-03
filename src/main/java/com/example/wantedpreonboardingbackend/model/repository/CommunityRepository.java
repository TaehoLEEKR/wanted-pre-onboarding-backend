package com.example.wantedpreonboardingbackend.model.repository;

import com.example.wantedpreonboardingbackend.model.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {
    Optional<Community> findByCommunityIdAndUser_UserId(Long communityId, Long userId);
    Optional<Community> findByCommunityId(Long communityId);
}
