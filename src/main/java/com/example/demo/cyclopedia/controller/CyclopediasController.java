package com.example.demo.cyclopedia.controller;

import com.example.demo.base.BaseController;
import com.example.demo.cyclopedia.pojo.Cyclopedias;
import com.example.demo.cyclopedia.pojo.CyclopediasCatalogues;
import com.example.demo.cyclopedia.pojo.CyclopediasCataloguesDetails;
import com.example.demo.cyclopedia.service.CyclopediasService;
import com.github.pagehelper.Page;
import commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-01-02-01 16:59
 */
@RestController
@RequestMapping("/cyclopedias")
public class CyclopediasController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CyclopediasController.class);

    @Autowired
    private CyclopediasService cyclopediasService;

    /**
    * @Author: DanierHe
    * @Date: 2020-02-05 下午 03:59
    * @Description: 百科搜索列表
    * @Param: [keyWord]
    * @return: java.util.Map
    */
    @RequestMapping("/selectByKeyWord")
    public Map selectByKeyWord(String keyWord){
        try {
            List<Cyclopedias> list = cyclopediasService.getCyclopediasByKeyWord(keyWord);
            return showSucc(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("CyclopediasController - selectByKeyWord - error");
            return error("查询失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-05 下午 03:59
    * @Description: 百科查看详情
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/getCyclopediasById")
    public Map getCyclopediasById(String id){
        try {
            Map<String ,Object> map = cyclopediasService.getCyclopediasById(id);
            return showSucc(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("CyclopediasController - getCyclopediasById - error");
            return error("查询错误，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-07 下午 04:29
    * @Description: 通过主表id查询附表   列表 list
    * @Param: [cyclopediasId]
    * @return: java.util.Map
    */
    @RequestMapping("/getCataloguesPageByCyclopediasId")
    public Map getCataloguesPageByCyclopediasId(String cyclopediasId,PageBean<CyclopediasCatalogues> pageBean){
        try {
            pageBean = cyclopediasService.getCataloguesPageBeanByCyclopediasId(cyclopediasId,pageBean);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("CyclopediasController - getCataloguesPageByCyclopediasId - error");
            return error("查询失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-07 下午 07:11
    * @Description: 保存目录信息
    * @Param: [name, cyclopediasId]
    * @return: java.util.Map
    */
    @RequestMapping("/saveCyclopediasCatalogues")
    public Map saveCyclopediasCatalogues(String name,String cyclopediasId){
        try {
            cyclopediasService.saveCyclopediasCatalogues(name,cyclopediasId);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("CyclopediasController - saveCyclopediasCatalogues - error");
            return error("操作失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-07 下午 07:18
    * @Description: 通过目录id，查询目录下的内容详情
    * @Param: [cataloguesId, pageBean]
    * @return: java.util.Map
    */
    @RequestMapping("/getDetailPageBeanByCataloguesId")
    public Map getDetailPageBeanByCataloguesId(String cataloguesId,PageBean<CyclopediasCataloguesDetails> pageBean){
        try {
            pageBean = cyclopediasService.getDetailPageBeanByCataloguesId(cataloguesId,pageBean);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("CyclopediasController - getDetailPageBeanByCataloguesId - error");
            return error("查询失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-07 下午 08:11
    * @Description: 保存内容
    * @Param: [cataloguesId, content]
    * @return: java.util.Map
    */
    @RequestMapping("/saveCataloguesDetails")
    public Map saveCataloguesDetails(String cyclopediasId,String cataloguesId,String content){
        try {
            cyclopediasService.saveCyclopediasCataloguesDetails(cyclopediasId,cataloguesId,content);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("CyclopediasController - saveCataloguesDetails - error");
            return error("操作失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-07 下午 08:34
    * @Description:    保存整个百科内容
    * @Param: [cyclopedias, cyclopediasId]
    * @return: java.util.Map
    */
    @RequestMapping("/saveCyclopediaInfo")
    public Map saveCyclopediaInfo(Cyclopedias cyclopedias,String cyclopediasId){
        try {
            cyclopediasService.saveCyclopediaInfo(cyclopedias,cyclopediasId);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("CyclopediasController - saveCyclopediaInfo - error");
            return error("操作失败！");
        }
    }
}
