package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.AppUser;
import com.derikwilson.mssqlclient.db.AppUserRepository;
import com.derikwilson.mssqlclient.db.UserInfoRepository;
import com.derikwilson.mssqlclient.db.UserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Service class for managing application users.
 * This class provides business logic and operations for {@link AppUser} entities by interacting with {@link AppUserRepository}.
 * It acts as a middle layer between the repository and the controller, ensuring that business rules and logic are applied to data before it is persisted or retrieved.
 */
@Service
public class AppUserService {

    private static final Logger log = LoggerFactory.getLogger(AppUserService.class);

    private final AppUserRepository appUserRepository;
    private final UserInfoRepository userInfoRepository;

    /**
     * Constructs an instance of AppUserService with a repository.
     *
     * @param appUserRepository The {@link AppUserRepository} to be used by this service for data access operations.
     */
    @Autowired
    public AppUserService(AppUserRepository appUserRepository, UserInfoRepository userInfoRepository) {
        this.appUserRepository = appUserRepository;
        this.userInfoRepository = userInfoRepository;
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

// In your AppUserService or a new service if it fits your architecture better

    @Transactional
    public AppUser createAppUserWithUserInfo(Long userId, String firstName, String lastName, String email) {
        AppUser newUser = new AppUser();
        newUser.setUserId(userId);
        // Set other necessary AppUser fields and defaults

        UserInfo userInfo = new UserInfo();
        userInfo.setUserGuid(newUser.getUserGuid()); // Assuming a relation based on UserGuid
        userInfo.setFirstName(firstName);
        userInfo.setLastName(lastName);
        userInfo.setEmail(email);
        // Set other necessary UserInfo fields

        // Save AppUser and UserInfo
        newUser = appUserRepository.save(newUser);
        userInfo = userInfoRepository.save(userInfo); // Assuming you have a userInfoRepository

        return newUser; // or return a DTO that includes information from both entities
    }


    /**
     * Saves or updates an application user in the repository.
     *
     * @param appUser The {@link AppUser} entity to be saved or updated.
     * @return The saved or updated {@link AppUser} entity.
     */
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving AppUser with USER_ID: {}", appUser.getUserId());
        return appUserRepository.save(appUser);
    }
}
