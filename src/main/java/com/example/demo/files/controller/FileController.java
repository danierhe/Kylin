package com.example.demo.files.controller;

import com.example.demo.base.BaseController;
import com.example.demo.files.pojo.FileInfo;
import com.example.demo.files.utils.FilePath;
import com.example.demo.files.service.FileInfoService;
import com.example.demo.files.utils.FilePathUtils;
import commons.DateUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-20-12-20 14:21
 */
@RestController
@RequestMapping("/files")
public class FileController extends BaseController {

    private static final String HOME_PATH= File.separator+"zyl"+File.separator+"loveU"+File.separator;
    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileInfoService fileInfoService;

    /** 
    * @Author: DanierHe
    * @Date: 2019-12-23 上午 10:23
    * @Description: 
    * @Param: [fileInfo]
    * @return: java.util.Map
    */
    @RequestMapping("/getFilesByFileTypeAndRemarks")
    public Map getFilesByFileTypeAndRemarks(FileInfo fileInfo){
        try {
            List<FileInfo> list = fileInfoService.getFileByFileTypeAndRemarks(fileInfo);
            return showSucc(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("FileController - getFilesByFileTypeAndRemarks - error");
            return error("查询失败！");
        }
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 03:54
    * @Description: 上传轮播图
    * @Param: [file]
    * @return: java.util.Map
    */
    @RequestMapping("/uploadPictures")
    public Map uploadPictures(@RequestParam("file") MultipartFile file,FileInfo info){
        try {
            if(file.isEmpty()){
                return error("请选择文件");
            }else {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                if(!suffix.equals("jpg") && !suffix.equals("png")){
                    return error("文件类型错误，请上传jpg,png格式的文件！");
                }
                FilePath filePath = new FilePath();
                filePath.setTime(DateUtils.getYmd());
                filePath.setUserId("danierhe");
                filePath.setFileType(info.getFileType());
                String path = FilePathUtils.getFilePath(filePath);
                String fullPath = HOME_PATH+path+ UUID.randomUUID()+"."+suffix;
                File newFile = new File(fullPath);
                FileUtils.copyInputStreamToFile(file.getInputStream(),newFile );
                //保存文件
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileName(file.getOriginalFilename());
                fileInfo.setFileType(info.getFileType());
                fileInfo.setRemarks(info.getRemarks());
                fileInfo.setFilePath(fullPath);
                fileInfoService.saveFiles(fileInfo);
                return showSucc("\\images"+fullPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("FileController - uploadMugShot - error");
            return error("上传失败！");
        }
    }


}
