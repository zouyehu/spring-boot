package com.fulan.springboot.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName User
 * @Author HU
 * @Date 2022/11/9 10:33
 * @Version 1.0
 */
@Data
@Builder
@ToString
public class User {

    private int id;
    private String username;
    private String password;
}
