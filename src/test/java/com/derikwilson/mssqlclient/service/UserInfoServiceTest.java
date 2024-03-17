package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.UserInfo;
import com.derikwilson.mssqlclient.db.UserInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@ExtendWith(MockitoExtension.class)
public class UserInfoServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceTest.class);

    @Mock
    private UserInfoRepository userInfoRepository;

    @InjectMocks
    private UserInfoService userInfoService;

    private UserInfo userInfo;
    private final UUID userGuid = UUID.randomUUID();
    private final String userEmail = "test@example.com";

    @BeforeEach
    void setUp() {
        userInfo = new UserInfo();
        userInfo.setUserGuid(userGuid);
        userInfo.setEmail(userEmail);
    }

    @Test
    void whenSaveOrUpdateUserInfo_thenUserInfoShouldBeSavedOrUpdated() {
        when(userInfoRepository.save(any(UserInfo.class))).thenReturn(userInfo);

        UserInfo savedOrUpdated = userInfoService.saveOrUpdateUserInfo(userInfo);

        assertNotNull(savedOrUpdated);
        assertEquals(userGuid, savedOrUpdated.getUserGuid());
        assertEquals(userEmail, savedOrUpdated.getEmail());
        verify(userInfoRepository).save(userInfo);
    }

    @Test
    public void whenFindUserInfoByGuid_thenUserInfoShouldBeReturned() {
        when(userInfoRepository.findByUserGuid(userGuid)).thenReturn(Collections.singletonList(userInfo));

        List<UserInfo> foundUserInfo = userInfoService.findUserInfoByGuid(userGuid);

        assertFalse(foundUserInfo.isEmpty());
        assertEquals(userEmail, foundUserInfo.get(0).getEmail());
        verify(userInfoRepository).findByUserGuid(userGuid);

        // Print the found user info for debugging purposes
        foundUserInfo.forEach(System.out::println);
    }

    // Additional tests...
}
