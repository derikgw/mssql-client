package com.derikwilson.mssqlclient.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

/**
 * Repository interface for {@link AppUser} entity. It extends {@link JpaRepository} to provide standard CRUD operations on the APP_USERS table.
 * Utilizes Spring Data JPA to abstract the need for boilerplate code required to implement data access layers for various persistence stores.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    /**
     * Finds a list of {@link AppUser} entities based on the user's unique identifier (UUID).
     * This method utilizes Spring Data JPA's query derivation mechanism to generate the corresponding query from the method name.
     *
     * @param userGUID The UUID of the user to search for.
     * @return A list of {@link AppUser} entities matching the provided UUID. This list may be empty if no users are found with the provided UUID.
     */
    List<AppUser> findByUserGuid(UUID userGUID);
}
