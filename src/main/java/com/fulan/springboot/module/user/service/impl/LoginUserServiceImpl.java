package com.fulan.springboot.module.user.service.impl;

import com.fulan.springboot.module.user.domain.LoginUser;
import com.fulan.springboot.module.user.mapper.LoginUserMapper;
import com.fulan.springboot.module.user.service.LoginUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName LoginUserServiceImpl
 * @Author HU
 * @Date 2022/7/26 16:35
 * @Version 1.0
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Resource
    private LoginUserMapper loginUserMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return loginUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(LoginUser record) {
        return loginUserMapper.insert(record);
    }

    @Override
    public int insertSelective(LoginUser record) {
        return loginUserMapper.insertSelective(record);
    }

    @Override
    public LoginUser selectByPrimaryKey(Integer userId) {
        return loginUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(LoginUser record) {
        return loginUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LoginUser record) {
        return loginUserMapper.updateByPrimaryKey(record);
    }

}
