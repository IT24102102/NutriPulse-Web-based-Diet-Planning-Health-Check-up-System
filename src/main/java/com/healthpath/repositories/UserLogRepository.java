package com.healthpath.repositories;

import com.healthpath.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserLogRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN user_contact uc ON u.user_id = uc.user_id WHERE uc.value = :email AND uc.contact_type = 'EMAIL'")
    Optional<User> findByEmail(@Param("email") String email);

}
