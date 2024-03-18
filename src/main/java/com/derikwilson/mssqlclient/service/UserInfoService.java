package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.UserInfo;
import com.derikwilson.mssqlclient.db.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing user information.
 * This class serves as an intermediary between the {@link UserInfoRepository} and the application's controllers (or other service consumers),
 * applying business logic and service-level operations on {@link UserInfo} entities.
 * It abstracts the complexity of data persistence and retrieval operations provided by the {@link UserInfoRepository}.
 */
@Service
public class UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    private final UserInfoRepository userInfoRepository;

    /**
     * Constructs an instance of UserInfoService with the specified userInfoRepository.
     *
     * @param userInfoRepository The {@link UserInfoRepository} to be used for accessing user information data.
     */
    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * Retrieves all user information records from the repository.
     *
     * @return A list of {@link UserInfo} entities. This list may be empty if no user information records are found.
     */
    public List<UserInfo> findAllUserInfo() {
        return userInfoRepository.findAll();
    }

    /**
     * Finds a single {@link UserInfo} entity by its user GUID.
     *
     * @param userGuid The UUID of the user to find information for.
     * @return The {@link UserInfo} entity associated with the provided UUID.
     * @throws javax.persistence.EntityNotFoundException if no entity exists for the provided UUID.
     */
    public UserInfo findUserInfoByGuid(UUID userGuid) {
        Optional<UserInfo> userInfoOptional = userInfoRepository.findById(userGuid);
        if (userInfoOptional.isPresent()) {
            return userInfoOptional.get();
        } else {
            log.error("User information not found for GUID: {}", userGuid);
            throw new EntityNotFoundException("User information not found for GUID: " + userGuid);
        }
    }

    /**
     * Saves or updates the user information entity in the repository.
     * This method can handle both creation of new user information records and updates to existing ones.
     *
     * @param userInfo The {@link UserInfo} entity to be saved or updated.
     * @return The saved or updated {@link UserInfo} entity.
     */
    @Transactional
    public UserInfo saveUserInfo(UserInfo userInfo) {
        // Perhaps add validation logic here
        return userInfoRepository.save(userInfo);
    }
}
