package com.fulan.springboot.module.user.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @ClassName LoginUserMapperTest
 * @Author HU
 * @Date 2022/7/26 15:15
 * @Version 1.0
 */
public class LoginUserMapperTest {
    private static LoginUserMapper mapper;

    @BeforeClass
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(LoginUserMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/LoginUserMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(LoginUserMapper.class, builder.openSession(true));
    }

    @Test
    public void testFindAllByUserId() {

        mapper.findAllByUserId(1);
    }
}
