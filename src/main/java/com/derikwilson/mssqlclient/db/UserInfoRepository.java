package com.derikwilson.mssqlclient.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository interface for {@link UserInfo} entities.
 * It extends {@link JpaRepository} to leverage Spring Data's standard CRUD and pagination capabilities
 * and to encapsulate the logic for accessing the USER_INFO table in the TEST_APP schema.
 * This repository interface facilitates abstraction over the underlying data access technology.
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {

    /**
     * Finds a list of {@link UserInfo} entities based on the user's unique identifier (UUID).
     * This method leverages Spring Data JPA's query derivation mechanism to automatically generate
     * the SQL query from the method name, ensuring a type-safe query that filters {@link UserInfo} records by their UUID.
     *
     * @param userGuid The UUID of the user for which to find {@link UserInfo} records.
     * @return A List of {@link UserInfo} entities that match the given user UUID.
     *         This list may be empty if no matching records are found.
     */
    List<UserInfo> findByUserGuid(UUID userGuid);
}
