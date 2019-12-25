package com.example.demo.files.utils;

import commons.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author DanierHe
 * @description 组装文件路径
 * @date 2019-12-20-12-20 16:06
 */
public class FilePathUtils {

    private Logger logger = LoggerFactory.getLogger(FilePathUtils.class);
    private static final String HOME_PATH= File.separator+"zyl"+File.separator+"loveU"+File.separator;
    /**
    * @Author: DanierHe
    * @Date: 2019-12-20 下午 04:15
    * @Description: 组织filepath :格式为： 时间/类型/用户
    * @Param: [filePath]
    * @return: java.lang.String
    */
    public static String getFilePath(FilePath filePath){
        if(filePath!=null){
            StringBuffer path = new StringBuffer();
            if(StringUtils.isBlank(filePath.getTime())){
                path.append(DateUtils.getYmd()+File.separator);
            }else {
                path.append(filePath.getTime()+File.separator);
            }

            if(StringUtils.isBlank(filePath.getFileType())){
                path.append("others"+File.separator);
            }else{
                path.append(filePath.getFileType()+File.separator);
            }

            if(StringUtils.isBlank(filePath.getUserId())){
                path.append("addmin"+File.separator);
            }else{
                path.append(filePath.getUserId()+File.separator);
            }
            String fullPath = HOME_PATH+path.toString();
            File directory = new File(fullPath);
            if(!directory.exists() && !directory.isDirectory()){
                directory.mkdirs();
            }
            return path.toString();
        }else{
            return "others";
        }
    }

}
