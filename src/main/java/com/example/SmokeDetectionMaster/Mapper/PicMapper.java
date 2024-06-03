package com.example.SmokeDetectionMaster.Mapper;

import com.example.SmokeDetectionMaster.Bean.Pic.ImportantScreenshotInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author ziyuan
 * @since 2024.04
 */
@Mapper
public interface PicMapper {
    @Insert("INSERT INTO importantpic (UserId,picId ,isRead, setTime) " +
            "VALUES (#{userId},#{picId}, 0, now())")
    int insertImportantPic(Integer userId, Integer picId);

    @Select("select ScreenshotRecordId,UserId from screenshotrecord where ScreenshotName=#{picName} limit 1")
    Map<String, Integer> getPicIdByPicName(String picName);

    @Delete("delete  from screenshotrecord  where ScreenshotName=#{name}")
    int deletePic(String name);
    @Update("update screenshotrecord set IsImportant = 'true' where ScreenshotName = #{name}")
    int likePic(String name);
    @Select("select  ImportantPicId from importantpic where picId=#{picID}")
    Integer checkPicIdExist(Integer picID );

    @Select("select s.ScreenshotPath,t.TerritoryId,t.TerritoryName from importantpic i left join  screenshotrecord s on i.picId=s.ScreenshotRecordId left join territory t on s.TerritoryId=t.TerritoryId where i.UserId=#{userId}")
    List<ImportantScreenshotInfo> findImportantScreenshotsByUserId(int userId);


//    @Select("SELECT * FROM importantpic WHERE ImportantPicId = #{importantPicId}")
//    Pic getPicById( int importantPicId);
//
//    @Select("SELECT * FROM importantpic WHERE UserId = #{userId}")
//    List<Pic> getPicsByUserId( int userId);
//
//    @Update("UPDATE importantpic SET Message = #{message}, isRead = #{isRead}, setTime = #{setTime} " +
//            "WHERE ImportantPicId = #{importantPicId}")
//    int updatePic(Pic pic);
//
//    @Delete("DELETE FROM importantpic WHERE ImportantPicId = #{importantPicId}")
//    int deletePic(int importantPicId);
}