package com.example.demo.weddings_funerals.service;

import com.example.demo.base.BaseService;
import com.example.demo.weddings_funerals.mapper.WeddingsFuneralsDetailsMapper;
import com.example.demo.weddings_funerals.mapper.WeddingsFuneralsMapper;
import com.example.demo.weddings_funerals.pojo.WeddingsFunerals;
import com.example.demo.weddings_funerals.pojo.WeddingsFuneralsDetails;
import com.example.demo.weddings_funerals.pojo.WeddingsFuneralsDetailsExample;
import com.example.demo.weddings_funerals.pojo.WeddingsFuneralsExample;
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

import java.math.BigDecimal;
import java.util.List;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-21-02-21 19:45
 */
@Service
@Transactional
public class WeddingsFuneralsService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(WeddingsFuneralsService.class);

    @Autowired
    private WeddingsFuneralsMapper mapper;
    @Autowired
    private WeddingsFuneralsDetailsMapper detailsMapper;

    //新增主表
    public int saveWeddingFuneral(WeddingsFunerals entity) throws Exception {
        entity.setId(getKey("wedding-funeral"));
        entity.setCreateTime(DateUtils.getYmdhms());
        entity.setStatus(USABLE_STATUS);
        return mapper.insert(entity);
    }
    //查询主表
    public WeddingsFunerals getWeddingsFuneralsById(String id) throws Exception {
        return mapper.selectByPrimaryKey(id);
    }

    //删除主表
    public void deleteWeddingsFuneralsById(String id) throws Exception {
        WeddingsFunerals weddingsFunerals = mapper.selectByPrimaryKey(id);
        weddingsFunerals.setStatus(DELETE_STATUS);
        weddingsFunerals.setUpdateTime(DateUtils.getYmdhms());
        mapper.updateByPrimaryKey(weddingsFunerals);
        //删除子表
        detailsMapper.updateByParentId(id);
    }

    //查询主表-列表
    public PageBean<WeddingsFunerals> getWeddingsFuneralsPage(PageBean<WeddingsFunerals> pageBean,String title,String startTime,String endTime) throws Exception {
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());

        WeddingsFuneralsExample example = new WeddingsFuneralsExample();
        WeddingsFuneralsExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(title)){
            criteria.andTitleEqualTo(title);
        }
        if(StringUtils.isNotBlank(startTime)){
            criteria.andEventDateGreaterThanOrEqualTo(startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            criteria.andEventDateLessThanOrEqualTo(endTime);
        }
        example.setOrderByClause("create_time desc");
        List<WeddingsFunerals> list = mapper.selectByExample(example);
        PageInfo<WeddingsFunerals> pageInfo = new PageInfo<>(list);
        pageBean.setData(list);
        pageBean.setCount((int) pageInfo.getTotal());
        return pageBean;
    }

    /*****************************************************************************************  附表信息  ****************************************************************************************/
    /*****************************************************************************************  附表信息  ****************************************************************************************/
    /*****************************************************************************************  附表信息  ****************************************************************************************/


    //新增--附表详情
    public void saveDetails(WeddingsFuneralsDetails details) throws Exception {
        details.setId(getKey("wedding-funeral-detail"));
        details.setStatus(USABLE_STATUS);
        details.setCreateTime(DateUtils.getYmdhms());
        detailsMapper.insert(details);
        //更新主表总金额
        WeddingsFunerals main = mapper.selectByPrimaryKey(details.getParentId());
        if(main!=null){
            main.setTotalMoney(getTotalMoneyByParentId(main.getId()));
            main.setUpdateTime(DateUtils.getYmdhms());
            mapper.updateByPrimaryKey(main);
        }
    }
    //删除-附表
    public void deleteDetailById(String id) throws Exception {
        WeddingsFuneralsDetails details = detailsMapper.selectByPrimaryKey(id);
        WeddingsFunerals weddingsFunerals=mapper.selectByPrimaryKey(details.getParentId());
        details.setUpdateTime(DateUtils.getYmdhms());
        details.setStatus(DELETE_STATUS);
        detailsMapper.updateByPrimaryKey(details);
        //更新主表金额
        weddingsFunerals.setTotalMoney(getTotalMoneyByParentId(details.getParentId()));
        mapper.updateByPrimaryKey(weddingsFunerals);
    }

    //查询金额
    private BigDecimal getTotalMoneyByParentId(String parentId) {
        WeddingsFuneralsDetails details = detailsMapper.getTotalMoneyByParentId(parentId);
        if(details!=null){
            return details.getGiftMoney();
        }else{
            return null;
        }
    }


    //查询详情，分页
    public PageBean<WeddingsFuneralsDetails> getDetailsPage(PageBean<WeddingsFuneralsDetails> pageBean,String parentId) throws Exception {
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        WeddingsFuneralsDetailsExample example = new WeddingsFuneralsDetailsExample();
        WeddingsFuneralsDetailsExample.Criteria  criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");
        criteria.andParentIdEqualTo(parentId);
        List<WeddingsFuneralsDetails> list = detailsMapper.selectByExample(example);
        PageInfo<WeddingsFuneralsDetails> pageInfo = new PageInfo<>(list);
        pageBean.setData(list);
        pageBean.setCount((int) pageInfo.getTotal());
        return pageBean;
    }

    //查询详情 - 全部
    public List<WeddingsFuneralsDetails> getDetailsList(String parentId) throws Exception {
        WeddingsFuneralsDetailsExample example = new WeddingsFuneralsDetailsExample();
        WeddingsFuneralsDetailsExample.Criteria  criteria = example.createCriteria();
        criteria.andStatusEqualTo("1");
        criteria.andParentIdEqualTo(parentId);
        List<WeddingsFuneralsDetails> list = detailsMapper.selectByExample(example);
        return list;
    }


}
