package com.healthpath.models;

import com.healthpath.models.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_log")
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "log_type", nullable = false, length = 50)
    private String logType;

    // Using BigDecimal with precision and scale
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal value;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "log_date")
    private LocalDateTime logDate;

    // Constructors
    public UserLog() {}

    public UserLog(User user, String logType, BigDecimal value, String notes, LocalDateTime logDate) {
        this.user = user;
        this.logType = logType;
        this.value = value;
        this.notes = notes;
        this.logDate = logDate;
    }

    // Getters and Setters
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDateTime logDate) {
        this.logDate = logDate;
    }
}
