package com.pet_portal.application.repository;

import com.pet_portal.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.roles LIKE %:role%")
    List<User> findUsersByRole(@Param("role") String role);

    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchUsersByUsername(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u WHERE u.adoptionApplications LIKE %:application%")
    List<User> findUsersByAdoptionApplication(@Param("application") String application);

    @Query("SELECT u FROM User u WHERE LOWER(u.reviews) LIKE LOWER(CONCAT('%', :word, '%'))")
    List<User> findUsersByReviewKeyword(@Param("word") String word);

    @Query("SELECT COUNT(u) FROM User u WHERE u.roles LIKE %:role%")
    long countUsersByRole(@Param("role") String role);

    @Query("SELECT u FROM User u WHERE u.reviews IS NULL OR u.reviews = ''")
    List<User> findUsersWithoutReviews();

    // @Query("SELECT u FROM User u WHERE u.email LIKE CONCAT('%', :domain)")
    // List<User> findUsersByEmailDomain(@Param("domain") String domain);
}