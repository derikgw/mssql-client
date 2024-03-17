package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.AppUser;
import com.derikwilson.mssqlclient.db.AppUserRepository;
import com.derikwilson.mssqlclient.db.UserInfo;
import com.derikwilson.mssqlclient.db.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("local") // This assumes a test profile that uses an in-memory database
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    public void whenSaveAppUser_thenAppUserIsSaved() {

        AppUser newUser = new AppUser();
        UserInfo newUserInfo = new UserInfo();

        newUser.setUserId(1234567890L);
        newUser.setActive(1);

        AppUser savedUser = appUserRepository.save(newUser);

        assertThat(savedUser).isNotNull();
        UUID userGuid = savedUser.getUserGuid();
        System.out.println("UserGuid: " + userGuid);
        assertThat(userGuid).isNotNull(); // Assuming your AppUser entity has an ID field

    }
}

