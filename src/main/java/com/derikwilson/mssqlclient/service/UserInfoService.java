package com.derikwilson.mssqlclient.service;

import com.derikwilson.mssqlclient.db.UserInfo;
import com.derikwilson.mssqlclient.db.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> findAllUserInfo() {
        return userInfoRepository.findAll();
    }

    public List<UserInfo> findUserInfoByGuid(UUID userGuid) {
        return userInfoRepository.findByUserGuid(userGuid);
    }

    @Transactional
    public UserInfo saveOrUpdateUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    // More methods as needed
}
