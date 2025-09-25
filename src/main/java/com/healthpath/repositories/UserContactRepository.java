package com.healthpath.repositories;

import com.healthpath.models.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserContactRepository extends JpaRepository<UserContact, Integer> {
    Optional<UserContact> findByValueAndContactType(String value, String contactType);

    // Optional helper for login by email
    Optional<UserContact> findByValue(String email); // assuming email is stored in "value" column
}
