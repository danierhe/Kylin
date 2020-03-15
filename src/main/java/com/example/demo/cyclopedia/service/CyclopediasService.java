package com.example.demo.cyclopedia.service;

import com.example.demo.base.BaseService;
import com.example.demo.cyclopedia.mapper.CyclopediasCataloguesDetailsMapper;
import com.example.demo.cyclopedia.mapper.CyclopediasCataloguesMapper;
import com.example.demo.cyclopedia.mapper.CyclopediasMapper;
import com.example.demo.cyclopedia.pojo.Cyclopedias;
import com.example.demo.cyclopedia.pojo.CyclopediasCatalogues;
import com.example.demo.cyclopedia.pojo.CyclopediasCataloguesDetails;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.DateUtils;
import commons.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-01-02-01 16:48
 */
@Service
@Transactional
public class CyclopediasService extends BaseService {

    @Autowired
    private CyclopediasMapper cyclopediasMapper;
    @Autowired
    private CyclopediasCataloguesMapper cataloguesMapper;
    @Autowired
    private CyclopediasCataloguesDetailsMapper detailsMapper;

    /**
    * @Author: DanierHe
    * @Date: 2020-02-01 下午 04:58
    * @Description: 通过关键字查询百科信息
    * @Param: [keyWord]
    * @return: java.util.List<com.example.demo.cyclopedia.pojo.Cyclopedias>
    */
    public List<Cyclopedias> getCyclopediasByKeyWord(String keyWord) throws Exception {
        List<Cyclopedias> list = cyclopediasMapper.selectCyclopediasByKeyWord(keyWord);
        return list;
    }


    /** 
    * @Author: DanierHe
    * @Date: 2020-02-05 下午 03:56
    * @Description: 查询百科详情
    * @Param: [id]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    public Map<String , Object> getCyclopediasById(String id) {
        Map<String , Object> map = new HashMap<>();
        //
        Cyclopedias cyclopedias = cyclopediasMapper.selectByPrimaryKey(id);
        map.put("cyclopedias",cyclopedias);
        //目录
        List<CyclopediasCatalogues> cyclopediasCataloguesList = cataloguesMapper.getCyclopediasCataloguesByCyclopediasId(id);
        map.put("cyclopediasCataloguesList",cyclopediasCataloguesList);
        //目录详情
        List<CyclopediasCataloguesDetails> detailsList = detailsMapper.getDetailsByCyclopediasId(id);
        map.put("detailsList",detailsList);
        return map;
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-07 下午 07:33
    * @Description: 通过主表id查询附表信息
    * @Param: [cyclopediasId, pageBean]
    * @return: commons.PageBean<com.example.demo.cyclopedia.pojo.CyclopediasCatalogues>
    */
    public PageBean<CyclopediasCatalogues> getCataloguesPageBeanByCyclopediasId(String cyclopediasId,PageBean<CyclopediasCatalogues> pageBean) throws Exception {
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<CyclopediasCatalogues> list = cataloguesMapper.getCyclopediasCataloguesByCyclopediasId(cyclopediasId);
        PageInfo<CyclopediasCatalogues> pageInfo = new PageInfo<>(list);
        pageBean.setCount((int) pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }

    //保存目录
    public void saveCyclopediasCatalogues(String name, String cyclopediasId) throws Exception {
        CyclopediasCatalogues catalogues = new CyclopediasCatalogues();
        catalogues.setId(getKey("cyclopediasCatalogues"));
        catalogues.setCyclopediaId(cyclopediasId);
        catalogues.setName(name);
        catalogues.setStatus(USABLE_STATUS);
        catalogues.setCreateTime(DateUtils.getYmdhms());
        cataloguesMapper.insert(catalogues);
    }

    //根据目录id查询所有的详情
    public PageBean<CyclopediasCataloguesDetails> getDetailPageBeanByCataloguesId(String cataloguesId, PageBean<CyclopediasCataloguesDetails> pageBean) throws Exception {
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<CyclopediasCataloguesDetails> list = detailsMapper.getDetailsByCatalogueId(cataloguesId);
        PageInfo<CyclopediasCataloguesDetails> pageInfo = new PageInfo<>(list);
        pageBean.setCount((int) pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }

    //保存每一条内容
    public void saveCyclopediasCataloguesDetails(String cyclopediasId,String cataloguesId, String content) throws Exception{
        CyclopediasCataloguesDetails details = new CyclopediasCataloguesDetails();
        details.setId(getKey("cataloguesDetails"));
        details.setCatalogueId(cataloguesId);
        details.setContent(content);
        details.setStatus(USABLE_STATUS);
        details.setCreateTime(DateUtils.getYmdhms());
        details.setCyclopediasId(cyclopediasId);
        detailsMapper.insert(details);
    }


    //保存全部百科内容
    public void saveCyclopediaInfo(Cyclopedias cyclopedias, String cyclopediasId) {
        cyclopedias.setId(getKey("cyclopedias"));
        cyclopedias.setStatus(USABLE_STATUS);
        cyclopedias.setCraeteTime(DateUtils.getYmdhms());
        cyclopediasMapper.insert(cyclopedias);
        //修改目录和内容中的主表id
        cataloguesMapper.updateCyclopediasId(cyclopedias.getId(),cyclopediasId);
        detailsMapper.updateCyclopediasId(cyclopedias.getId(),cyclopediasId);
    }
}
