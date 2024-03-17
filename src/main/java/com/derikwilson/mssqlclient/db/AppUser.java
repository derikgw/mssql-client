package com.derikwilson.mssqlclient.db;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "APP_USERS", schema = "TEST_APP")
public class AppUser {

    @Id
    @GeneratedValue // Instructs JPA to use the SQL Server's GUID type for the primary key
    @Column(name = "USER_GUID", columnDefinition = "uniqueidentifier", updatable = false, nullable = false)
    private UUID userGuid;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Column(name = "ACTIVE")
    private Integer active;

    public UUID getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    // Getters and setters omitted for brevity
}

