package com.example.demo.files.service;

import com.example.demo.base.BaseService;
import com.example.demo.files.mapper.FileInfoMapper;
import com.example.demo.files.pojo.FileInfo;
import com.example.demo.files.pojo.FileInfoExample;
import commons.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-20-12-20 14:45
 */
@Service
@Transactional
public class FileInfoService extends BaseService {

    @Autowired
    private FileInfoMapper fileInfoMapper;


    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 03:50
    * @Description: 保存文件信息
    * @Param: [fileInfo]
    * @return: int
    */
    public int saveFiles(FileInfo fileInfo) throws Exception{
        fileInfo.setId(getKey("file"));
        fileInfo.setCreateTime(DateUtils.getYmdhms());
        fileInfo.setStatus(USABLE_STATUS);
        return fileInfoMapper.insert(fileInfo);
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 03:51
    * @Description: 根据id查询文件信息。
    * @Param: [fileId]
    * @return: com.example.demo.files.pojo.FileInfo
    */
    public FileInfo getFileById(String fileId)throws Exception{
        return fileInfoMapper.selectByPrimaryKey(fileId);
    }


    public FileInfo getFileByUserIdAndFileType(String userId,String fileType) throws Exception{
        return fileInfoMapper.getFileByUserIdAndFileType(userId,fileType);
    }


    public List<FileInfo> getFileByFileTypeAndRemarks(FileInfo fileInfo) throws Exception{
        FileInfoExample example = this.getExample(fileInfo);
        return fileInfoMapper.selectByExample(example);
    }



    private FileInfoExample getExample(FileInfo fileInfo){
        FileInfoExample example = new FileInfoExample();
        FileInfoExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(fileInfo.getFileType())){
            criteria.andFileTypeEqualTo(fileInfo.getFileType());
        }
        if(StringUtils.isNotBlank(fileInfo.getRemarks())){
            criteria.andRemarksEqualTo(fileInfo.getRemarks());
        }
        return example;
    }
}
