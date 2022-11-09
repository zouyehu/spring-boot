package com.fulan.springboot.module.user.mapper;

import com.fulan.springboot.module.user.domain.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName LoginUserMapper
 * @Author HU
 * @Date 2022/7/26 14:23
 * @Version 1.0
 */
@Mapper
public interface LoginUserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(LoginUser record);

    int insertSelective(LoginUser record);

    LoginUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(LoginUser record);

    int updateByPrimaryKey(LoginUser record);

    /**
     * 通过用户ID查询
     *
     * @param userId
     * @return
     */
    List<LoginUser> findAllByUserId(@Param("userId") Integer userId);

    List<LoginUser> findAllByUserName(@Param("userName") String userName);


}