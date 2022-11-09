package com.fulan.springboot.module.user.service;

import com.fulan.springboot.module.user.domain.LoginUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.fulan.springboot.module.user.mapper.LoginUserMapper;

/**
 *
 * @ClassName LoginUserService
 * @Author HU
 * @Date 2022/7/26 14:23
 * @Version 1.0
 */   
public interface LoginUserService{

    public int deleteByPrimaryKey(Integer userId);

    
    public int insert(LoginUser record);

    
    public int insertSelective(LoginUser record);

    
    public LoginUser selectByPrimaryKey(Integer userId);

    
    public int updateByPrimaryKeySelective(LoginUser record);

    
    public int updateByPrimaryKey(LoginUser record);

}
