package com.example.chapter6_wzy.Mapper.User;

import com.example.chapter6_wzy.Bean.Smoke.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author wsh
 */
@Mapper
public interface LoginMapper {
    @Select("select * from user where Username=#{username} and Password=#{password} and Role=#{role} limit 1")
    User checkLogin(String username, String password, String role);

}
