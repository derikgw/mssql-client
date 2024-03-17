package com.derikwilson.mssqlclient.db;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Represents an application user within the system, mapping to the APP_USERS table in the TEST_APP schema.
 * This entity class is used to encapsulate user-related information such as user identification, last update timestamp, and active status.
 */
@Entity
@Table(name = "APP_USERS", schema = "TEST_APP")
public class AppUser {

    /**
     * The unique identifier for the user, represented as a UUID. This is the primary key in the database and is automatically generated.
     * It is mapped to the USER_GUID column in the APP_USERS table and is defined as a uniqueidentifier type in SQL Server.
     */
    @Id
    @GeneratedValue // Instructs JPA to use the SQL Server's GUID type for the primary key
    @Column(name = "USER_GUID", columnDefinition = "uniqueidentifier", updatable = false, nullable = false)
    private UUID userGuid;

    /**
     * The user's identification number. This is a unique identifier for the user within the application, separate from the database primary key.
     * It is mapped to the USER_ID column in the APP_USERS table and cannot be null.
     */
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    /**
     * The timestamp of the last update made to the user's information. This is mapped to the LAST_UPDATE column in the APP_USERS table.
     * The TemporalType.TIMESTAMP annotation indicates that both date and time are included in the timestamp.
     */
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    /**
     * Represents whether the user is active. This is an integer value where specific numbers represent the user's active status.
     * It is mapped to the ACTIVE column in the APP_USERS table.
     */
    @Column(name = "ACTIVE")
    private Integer active;

    // Getter and setter methods with Javadoc comments

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
     * @param userGuid The UUID to set for the user.
     */
    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }

    /**
     * Gets the identification number of the user.
     *
     * @return The identification number of the user.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the identification number of the user.
     *
     * @param userId The identification number to set for the user.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the timestamp of the last update made to the user's information.
     *
     * @return The timestamp of the last update.
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the timestamp of the last update made to the user's information.
     *
     * @param lastUpdate The timestamp of the last update to set.
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Gets the user's active status.
     *
     * @return The active status of the user.
     */
    public Integer getActive() {
        return active;
    }

    /**
     * Sets the user's active status.
     *
     * @param active The active status to set for the user.
     */
    public void setActive(Integer active) {
        this.active = active;
    }
}
