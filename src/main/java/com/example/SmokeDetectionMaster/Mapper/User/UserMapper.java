package com.example.SmokeDetectionMaster.Mapper.User;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

/**
 * @author ziyuan
 * @since 2024.02
 */
@Mapper
public interface UserMapper {


    @Select("SELECT COUNT(*) FROM user WHERE Username = #{userName}")
    Integer checkIsReg(String userName);

    @Select("SELECT * FROM user WHERE UserID = #{id}")
    User findById(Integer id);

    @Select("SELECT * FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(username, role, password, salt, telephone, regtime, enabled) " +
            "VALUES(#{username}, #{role}, #{password}, #{salt}, #{telephone}, #{regTime}, #{enabled})")

    int insert(User user);

    @Update("UPDATE user SET username=#{username}, role=#{role}, password=#{password}, " +
            "salt=#{salt}, telephone=#{telephone}, regtime=#{regTime}, enabled=#{enabled} WHERE id = #{id}")
    int update(User user);

    @Delete("DELETE FROM user WHERE UserID = #{id}")
    int delete(Integer id);
}