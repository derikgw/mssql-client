package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.AppUser;
import com.derikwilson.mssqlclient.db.AppUserRepository;
import com.derikwilson.mssqlclient.db.UserInfo;
import com.derikwilson.mssqlclient.db.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local") // This assumes a test profile that uses an in-memory database
public class AppUserRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(AppUserRepositoryTest.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void whenSaveAppUser_thenAppUserIsSaved() {

        AppUser newUser = new AppUser();
        UserInfo newUserInfo = new UserInfo();

        newUser.setUserId(1234567891L);
        newUser.setActive(1);

        AppUser savedUser = appUserRepository.save(newUser);

        newUserInfo.setUserGuid(newUser.getUserGuid());
        newUserInfo.setEmail("userguy@example.com");
        userInfoRepository.save(newUserInfo);

        newUser = new AppUser();

        newUser.setUserId(1234567892L);
        newUser.setActive(1);

        savedUser = appUserRepository.save(newUser);

        assertThat(savedUser).isNotNull();
        UUID userGuid = savedUser.getUserGuid();
        assertThat(userGuid).isNotNull(); // Assuming your AppUser entity has an ID field

        newUserInfo.setUserGuid(newUser.getUserGuid());
        newUserInfo.setEmail("userguy2@example.com");
        userInfoRepository.save(newUserInfo);

        assertThat(newUserInfo).isNotNull();
        UUID userInfoGUID = newUserInfo.getUserGuid();
        assertThat(userInfoGUID).isNotNull();

        String userEmail = newUserInfo.getEmail();
        assertThat(userEmail).isNotNull();

        List<AppUser> users = appUserRepository.findAll();

        for (AppUser appUser : users) {
            UserInfo userInfo = userInfoRepository.getById(appUser.getUserGuid());

            logger.info("User: " + appUser.getUserGuid() + ", " + appUser.getUserId() +
                    ", " + appUser.getActive() + ", " + userInfo.getEmail());
        }

    }
}

