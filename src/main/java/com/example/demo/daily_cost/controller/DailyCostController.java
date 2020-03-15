package com.example.demo.daily_cost.controller;

import com.example.demo.base.BaseController;
import com.example.demo.daily_cost.pojo.DailyCost;
import com.example.demo.daily_cost.service.DailyCostService;
import commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-21-02-21 16:04
 */
@RestController
@RequestMapping("/daily_cost")
public class DailyCostController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(DailyCostController.class);

    @Autowired
    private DailyCostService dailyCostService;

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 04:08
    * @Description: 保存
    * @Param: [dailyCost]
    * @return: java.util.Map
    */
    @RequestMapping("/saveDailyCost")
    public Map saveDailyCost(DailyCost dailyCost){
        try {
            int i = dailyCostService.saveDailyCost(dailyCost);
            if(i==1){
                return success();
            }else{
                return error("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("DailyCostController - saveDailyCost - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 07:19
    * @Description: 删除   通过主键删除
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/deleteDailyCostById")
    public Map deleteDailyCostById(String id){
        try {
            int i = dailyCostService.deleteDailyCost(id);
            if(i==1){
                return success();
            }else {
                return error("删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("DailyCostController - deleteDailyCostById - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 04:10
    * @Description: 根据id，查询详情
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/selectDailyCostById")
    public Map selectDailyCostById(String id){
        try {
            DailyCost dailyCost = dailyCostService.getDailyCostById(id);
            return showSucc(dailyCost);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("DailyCostController - selectDailyCostById - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 04:11
    * @Description: 编辑更新
    * @Param: [dailyCost]
    * @return: java.util.Map
    */
    @RequestMapping("/updateDailyCost")
    public Map updateDailyCost(DailyCost dailyCost){
        try {
            int i = dailyCostService.updateDailyCost(dailyCost);
            if(i==1){
                return success();
            }else{
                return error("更新失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("DailyCostController - updateDailyCost - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 04:17
    * @Description: 查询-列表
    * @Param: [pageBean, name, startTime, endTime]
    * @return: java.util.Map
    */
    @RequestMapping("/getDailyCostPage")
    public Map getDailyCostPage(PageBean<DailyCost> pageBean,String name,String startTime,String endTime){
        try {
            pageBean = dailyCostService.getDailyCostPage(pageBean,name,startTime,endTime);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("DailyCostController - getDailyCostPage - error");
            return error("操作失败，请联系管理员！");
        }
    }
}
