package com.example.demo.students.service;

import com.example.demo.base.BaseService;
import com.example.demo.students.mapper.StudentsMapper;
import com.example.demo.students.pojo.Students;
import com.example.demo.students.pojo.StudentsExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.DateUtils;
import commons.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-22-02-22 22:24
 */
@Service
@Transactional
public class StudentService extends BaseService {
    private Logger logger= LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentsMapper studentsMapper;

    //新增学生
    public void saveStudents(Students students) throws Exception {
        students.setId(getKey("students"));
        students.setStatus(USABLE_STATUS);
        students.setCreateTime(DateUtils.getYmdhms());
        students.setMaxCheck(0);
        studentsMapper.insert(students);
    }
    //查询学生列表
    public PageBean<Students> getStudentsPage(PageBean<Students> pageBean,Students students) throws Exception {
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());

        StudentsExample example = new StudentsExample();
        StudentsExample.Criteria criteria = example.createCriteria();
        if(students!=null || !"".equals(students)){
            //学生姓名
            if(StringUtils.isNotBlank(students.getStudentName())){
                criteria.andStudentNameLike("%"+students.getStudentName()+"%");
            }
            //微信名
            if(StringUtils.isNotBlank(students.getWechatName())){
                criteria.andWechatNameLike("%"+students.getWechatName()+"%");
            }
            //邮箱
            if(StringUtils.isNotBlank(students.getEmail())){
                criteria.andEmailLike("%"+students.getEmail()+"%");
            }
            //电话
            if(StringUtils.isNotBlank(students.getPhone())){
                criteria.andPhoneLike("%"+students.getPhone()+"%");
            }
            //性别
            if(StringUtils.isNotBlank(students.getSex())){
                criteria.andSexEqualTo(students.getSex());
            }
            //证件号
            if(StringUtils.isNotBlank(students.getCardNumber())){
                criteria.andCardNumberLike("%"+students.getCardNumber()+"%");
            }
        }
        example.setOrderByClause(" create_time desc ");

        List<Students> list = studentsMapper.selectByExample(example);
        PageInfo<Students> pageInfo = new PageInfo<>(list);
        pageBean.setData(list);
        pageBean.setCount((int) pageInfo.getTotal());
        return pageBean;
    }

    //根据id，查询学生详情
    public Students selectStudentById(String id) throws Exception {
        return studentsMapper.selectByPrimaryKey(id);
    }

    //删除学生
    public int deleteStudentsById(String id) throws Exception{
        Students students = studentsMapper.selectByPrimaryKey(id);
        students.setStatus(DELETE_STATUS);
        students.setUpdateTime(DateUtils.getYmdhms());
        return studentsMapper.updateByPrimaryKey(students);
    }

    //更新信息
    public int updateStudents(Students students) throws Exception{
        Students old = studentsMapper.selectByPrimaryKey(students.getId());
        old.setStudentName(students.getStudentName());
        old.setWechatName(students.getWechatName());
        old.setEmail(students.getEmail());
        old.setPhone(students.getPhone());
        old.setBirthday(students.getBirthday());
        old.setSex(students.getSex());
        old.setCardNumber(students.getCardNumber());
        old.setRemarks(students.getRemarks());
        old.setUpdateTime(DateUtils.getYmdhms());
        return studentsMapper.updateByPrimaryKey(old);
    }






}
