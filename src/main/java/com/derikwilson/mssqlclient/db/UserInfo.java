package com.derikwilson.mssqlclient.db;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USER_INFO", schema = "TEST_APP")
public class UserInfo {

    @Id
    @Column(name = "USER_GUID", columnDefinition = "uniqueidentifier", nullable = false)
    private UUID userGuid; // This is now the primary key

    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    // Since USER_GUID is the primary key, you no longer need a separate 'infoId' field or getter/setter for it.

    // Constructors, Getters, and Setters

    public UUID getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Omitted hashCode, equals, and toString methods for brevity
}
