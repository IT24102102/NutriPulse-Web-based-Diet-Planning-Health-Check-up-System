package com.healthpath.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_contact") // must match your DB table name
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contact_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "contact_type", nullable = false)
    private String contactType;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    // Getters and setters
    public Integer getContact_id() { return contact_id; }
    public void setContact_id(Integer contact_id) { this.contact_id = contact_id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getContactType() { return contactType; }
    public void setContactType(String contactType) { this.contactType = contactType; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public Boolean getIsPrimary() { return isPrimary; }
    public void setIsPrimary(Boolean isPrimary) { this.isPrimary = isPrimary; }

    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
}
