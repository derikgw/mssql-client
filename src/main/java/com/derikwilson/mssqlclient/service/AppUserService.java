package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.AppUser;
import com.derikwilson.mssqlclient.db.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing application users.
 * This class provides business logic and operations for {@link AppUser} entities by interacting with {@link AppUserRepository}.
 * It acts as a middle layer between the repository and the controller, ensuring that business rules and logic are applied to data before it is persisted or retrieved.
 */
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    /**
     * Constructs an instance of AppUserService with a repository.
     *
     * @param appUserRepository The {@link AppUserRepository} to be used by this service for data access operations.
     */
    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * Retrieves all application users from the repository.
     *
     * @return A list of {@link AppUser} entities. This list may be empty if no users are found.
     */
    public List<AppUser> getAllAppUsers() {
        return appUserRepository.findAll();
    }

    /**
     * Retrieves application users by their unique user GUID.
     *
     * @param userGuid The UUID of the user to search for.
     * @return A list of {@link AppUser} entities matching the provided UUID. This list may be empty if no matching users are found.
     */
    public List<AppUser> getAppUserByUserGuid(UUID userGuid) {
        return appUserRepository.findByUserGuid(userGuid);
    }

    /**
     * Saves or updates an application user in the repository.
     *
     * @param appUser The {@link AppUser} entity to be saved or updated.
     * @return The saved or updated {@link AppUser} entity.
     */
    public AppUser saveAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
