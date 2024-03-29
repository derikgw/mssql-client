package com.derikwilson.mssqlclient.db;

import javax.persistence.*;
import java.util.UUID;

/**
 * Entity class representing the USER_INFO table within the TEST_APP schema of the database.
 * This class is used to store and retrieve user email information associated with a unique user identifier (UUID).
 * The user's unique identifier (UUID) serves as the primary key in the database.
 */
@Entity
@Table(name = "USER_INFO", schema = "TEST_APP")
public class UserInfo {

    /**
     * The unique identifier for the user, serving as the primary key in the USER_INFO table.
     * This UUID is used to uniquely identify a user within the application.
     */
    @Id
    @Column(name = "USER_GUID", columnDefinition = "uniqueidentifier", nullable = false)
    private UUID userGuid;

    /**
     * The email address of the user. This field is required and has a maximum length of 255 characters.
     * It is stored in the EMAIL column of the USER_INFO table.
     */
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    // Constructors, Getters, and Setters

    /**
     * Gets the unique identifier (UUID) of the user.
     *
     * @return The UUID of the user.
     */
    public UUID getUserGuid() {
        return userGuid;
    }

    /**
     * Sets the unique identifier (UUID) of the user.
     *
     * @param userGuid The UUID to be set for the user.
     */
    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The email address to be set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
