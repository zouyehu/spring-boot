package com.fulan.springboot.module.user.controller;

import com.fulan.springboot.module.user.domain.LoginUser;
import com.fulan.springboot.module.user.service.LoginUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (login_user)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/login_user")
public class LoginUserController {

    @Resource
    private LoginUserService loginUserService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public LoginUser selectOne(Integer id) {
        return loginUserService.selectByPrimaryKey(id);
    }

}
