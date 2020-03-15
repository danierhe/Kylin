package com.example.demo.students.service;

import com.example.demo.base.BaseService;
import com.example.demo.students.mapper.StudentsMapper;
import com.example.demo.students.mapper.StudentsSignMapper;
import com.example.demo.students.pojo.StudentsSign;
import commons.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-22-02-22 22:48
 */
@Service
@Transactional
public class StudentSignService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(StudentSignService.class);

    @Autowired
    private StudentsSignMapper signMapper;
    @Autowired
    private StudentsMapper studentsMapper;


    //新增签到信息
    public void saveStudentsSign(StudentsSign studentsSign) throws Exception{
        //检查今天是否已经签到
        StudentsSign sign = signMapper.SignOrNot(studentsSign.getStudentId(),DateUtils.getYmd());
        if(sign!=null){
            //当天已签到
        }else{
            //当天未签到

            //查询上次签到信息


            studentsSign.setId(getKey("student-sign"));
            studentsSign.setCreateTime(DateUtils.getYmdhms());
            studentsSign.setSignTime(DateUtils.getYmdhms());
            studentsSign.setSignFlag("1");
            studentsSign.setStatus(USABLE_STATUS);
            signMapper.insert(studentsSign);
        }

    }

}
