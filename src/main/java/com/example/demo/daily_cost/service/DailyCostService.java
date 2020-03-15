package com.example.demo.daily_cost.service;

import com.example.demo.base.BaseService;
import com.example.demo.daily_cost.mapper.DailyCostMapper;
import com.example.demo.daily_cost.pojo.DailyCost;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.DateUtils;
import commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-21-02-21 15:43
 */
@Service
@Transactional
public class DailyCostService extends BaseService {

    private Logger logger = LoggerFactory.getLogger(DailyCostService.class);

    @Autowired
    private DailyCostMapper dailyCostMapper;

    //新增
    public int saveDailyCost(DailyCost dailyCost) throws Exception {
        dailyCost.setId(getKey("dailyCost"));
        dailyCost.setStatus(USABLE_STATUS);
        dailyCost.setCreateTime(DateUtils.getYmdhms());
        return dailyCostMapper.insert(dailyCost);
    }
    //删除
    public int deleteDailyCost(String id) throws Exception {
        DailyCost dailyCost = dailyCostMapper.selectByPrimaryKey(id);
        dailyCost.setUpdateTime(DateUtils.getYmdhms());
        dailyCost.setStatus(DELETE_STATUS);
        return dailyCostMapper.updateByPrimaryKey(dailyCost);
    }

    //查询
    public DailyCost getDailyCostById(String id) throws Exception {
        return dailyCostMapper.selectByPrimaryKey(id);
    }

    //更新编辑
    public int updateDailyCost(DailyCost dailyCost) throws Exception {
        dailyCost.setUpdateTime(DateUtils.getYmdhms());
        return dailyCostMapper.updateByPrimaryKey(dailyCost);
    }

    //查询Page
    public PageBean<DailyCost> getDailyCostPage(PageBean<DailyCost> pageBean,String name,String startTime,String endTime) throws Exception {
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<DailyCost> list = dailyCostMapper.getDailyCostList(name,startTime,endTime);
        PageInfo<DailyCost> pageInfo = new PageInfo<>(list);
        pageBean.setData(list);
        pageBean.setCount((int) pageInfo.getTotal());
        return pageBean;
    }


}
