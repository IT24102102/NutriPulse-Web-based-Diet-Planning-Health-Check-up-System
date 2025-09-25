package com.healthpath.repositories;

import com.healthpath.models.User;
import com.healthpath.models.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Custom query to get User by email stored in UserContact
    @Query("SELECT u FROM UserContact u WHERE u.value = :email")
    Optional<UserContact> findByValue(@Param("email") String email);

}
