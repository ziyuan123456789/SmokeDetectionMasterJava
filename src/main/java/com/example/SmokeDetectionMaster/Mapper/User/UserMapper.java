package com.example.SmokeDetectionMaster.Mapper.User;

import com.example.SmokeDetectionMaster.Bean.Smoke.User;
import com.example.SmokeDetectionMaster.Bean.User.Dto.UserAdminViewDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    // 列出所有未被封禁的用户
    @Select("SELECT UserID, Username, Role, Telephone, regTime, Enabled FROM user WHERE Enabled = '1'")
    List<UserAdminViewDto> findAllActiveUsers();

    // 列出所有用户
    @Select("SELECT UserID, Username, Role, Telephone, regTime, Enabled FROM user")
    List<UserAdminViewDto> findAllUsers();

    // 按照ID找到用户
    @Select("SELECT UserID, Username, Role, Telephone, regTime, Enabled FROM user where UserID=#{userId}")
    UserAdminViewDto findById(int userId);

    // 封禁用户
    @Update("UPDATE user SET Enabled = '0' WHERE UserID = #{userId}")
    void banUser(int userId);

    // 解封用户
    @Update("UPDATE user SET Enabled = '1' WHERE UserID = #{userId}")
    void unbanUser(int userId);

    // 增加用户
    @Insert("INSERT INTO user (Username, Role,Password,Salt , Telephone,  Enabled) " +
            "VALUES (#{username},0, #{password},123, #{telephone},1)")
    Integer createUser(User user);

    // 更新用户信息
    @Update("UPDATE user SET Username = #{username}, Telephone = #{telephone} WHERE UserID = #{userId}")
    void updateUserInfo(int userId, String username, String telephone);

    // 修改用户密码
    @Update("UPDATE user SET Password = #{password} WHERE UserID = #{userId}")
    void updateUserPassword(int userId, String password);
}