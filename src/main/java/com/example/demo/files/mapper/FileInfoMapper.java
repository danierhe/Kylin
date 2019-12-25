package com.example.demo.files.mapper;

import com.example.demo.files.pojo.FileInfo;
import com.example.demo.files.pojo.FileInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoMapper {
    long countByExample(FileInfoExample example);

    int deleteByExample(FileInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(FileInfo record);

    int insertSelective(FileInfo record);

    List<FileInfo> selectByExample(FileInfoExample example);

    FileInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByExample(@Param("record") FileInfo record, @Param("example") FileInfoExample example);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 06:28
    * @Description: 根据用户id和文件类型查询文件
    * @Param: [userId, fileType]
    * @return: com.example.demo.files.pojo.FileInfo
    */
    @Select("select * from files where status=1 and user_id=#{userId} and file_type=#{fileType}")
    FileInfo getFileByUserIdAndFileType(@Param("userId") String userId, @Param("fileType") String fileType);
}