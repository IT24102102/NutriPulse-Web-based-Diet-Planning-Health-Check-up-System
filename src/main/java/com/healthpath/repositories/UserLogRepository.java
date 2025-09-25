package com.healthpath.repositories;

import com.healthpath.model.UserLog;
import com.healthpath.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {

    // Find all logs for a specific user
    List<UserLog> findByUser(User user);

    // Optional: find by user and log type
    List<UserLog> findByUserAndLogType(User user, String logType);
}
