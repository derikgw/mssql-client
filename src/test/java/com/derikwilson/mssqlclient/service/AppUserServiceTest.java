package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.AppUser;
import com.derikwilson.mssqlclient.db.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({AppUserService.class, UserInfoService.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local") // This assumes a test profile that uses an in-memory database
public class AppUserServiceTest { // Renaming to reflect we're testing the service

    private static final Logger logger = LoggerFactory.getLogger(AppUserServiceTest.class);

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void whenSaveAppUser_thenAppUserIsSaved() {
        AppUser newUser = new AppUser();

        // Step 1: Save a user using AppUserService
        newUser.setUserId(1234567891L);
        newUser.setActive(1);

        AppUser savedUser = appUserService.saveAppUser(newUser);

        UserInfo newUserInfo = new UserInfo();
        newUserInfo.setUserGuid(newUser.getUserGuid());
        newUserInfo.setEmail("userguy@example.com");
        userInfoService.saveUserInfo(newUserInfo); // Assuming direct use for non-service covered actions

        // Assertion to check if AppUser was saved correctly
        assertThat(savedUser).isNotNull();
        UUID userGuid = savedUser.getUserGuid();
        assertThat(userGuid).isNotNull();

        // Step 2: Save another user for ensuring list and retrieval works
        newUser = new AppUser();
        newUser.setUserId(1234567892L);
        newUser.setActive(1);

        savedUser = appUserService.saveAppUser(newUser);
        assertThat(savedUser).isNotNull();

        newUserInfo = new UserInfo(); // Resetting newUserInfo object
        newUserInfo.setUserGuid(newUser.getUserGuid());
        newUserInfo.setEmail("userguy2@example.com");
        userInfoService.saveUserInfo(newUserInfo);

        // Assertions to check UserInfo saving logic
        assertThat(newUserInfo).isNotNull();
        UUID userInfoGUID = newUserInfo.getUserGuid();
        assertThat(userInfoGUID).isNotNull();
        assertThat(newUserInfo.getEmail()).isEqualTo("userguy2@example.com");

        // Utilizing service to retrieve all users and assert
        List<AppUser> users = appUserService.getAllAppUsers();
        assertThat(users).hasSize(2); // Ensure we have saved two users

        for (AppUser appUser : users) {
            UserInfo userInfo = userInfoService.findUserInfoByGuid(appUser.getUserGuid()); // Direct call for checking related info

            logger.info("User: " + appUser.getUserGuid() + ", " + appUser.getUserId() +
                    ", " + appUser.getActive() + ", " + userInfo.getEmail());
        }
    }
}