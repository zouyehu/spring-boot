package com.fulan.springboot.module.user.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 *
 * @ClassName LoginUser
 * @Author HU
 * @Date 2022/7/26 14:23
 * @Version 1.0
 */   
/**
    * login_user
    */
@Data
public class LoginUser implements Serializable {
    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * userName
    */
    private String userName;

    /**
    * birth
    */
    private Date birth;

    /**
    * 工资
    */
    private BigDecimal salary;

    private static final long serialVersionUID = 1L;
}