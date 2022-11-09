package com.fulan.springboot.controller;

import com.fulan.springboot.domain.User;
import com.uoqian.encryptionutil.annotation.Encrypt;
import com.uoqian.encryptionutil.response.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author HU
 * @Date 2022/11/9 10:30
 * @Version 1.0
 */
@RestController
public class TestController {

    @GetMapping(value = "/user", produces = "application/json;charset=utf-8")
    @Encrypt
    public RespBean getUser() {
        User user = User.builder().id(1).username("胡歌").password("123123").build();
        return RespBean.ok("ok", user);
    }
}
