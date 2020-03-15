package com.example.demo.invoice.controller;

import com.example.demo.base.BaseController;
import com.example.demo.invoice.pojo.InvoiceDetail;
import com.example.demo.invoice.pojo.InvoiceList;
import com.example.demo.invoice.service.InvoiceListService;
import commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-25-12-25 14:35
 */
@RestController
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceListService listService;


    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 02:45
    * @Description: 保存发票主表
    * @Param: [invoiceList]
    * @return: java.util.Map
    */
    @RequestMapping("/saveInvoiceList")
    public Map saveInvoiceList(InvoiceList invoiceList){
        try {
            List<InvoiceDetail> detailList = listService.getDetailListByListId(invoiceList.getId());
            if(detailList.size()<1){
                return error("请添加发票详情！");
            }
            loginUser = getLoginUser();
            invoiceList.setUserId(loginUser.getUserId());
            invoiceList.setCompanyId(loginUser.getCompanyId());
            int i = listService.saveInvoiceList(invoiceList);
            if(i==1){
                return success();
            }else {
                return error("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - saveInvoiceList - error");
            return error("操作失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:17
    * @Description: 查询发票主表
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/getInvoiceListById")
    public Map getInvoiceListById(String listId){
        try {
            InvoiceList invoiceList = listService.getInvoiceListById(listId);
            return showSucc(invoiceList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - getInvoiceListById - error");
            return error("操作失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:19
    * @Description: 更新 发票主表信息
    * @Param: [invoiceList]
    * @return: java.util.Map
    */
    @RequestMapping("/updateInvoiceList")
    public Map updateInvoiceList(InvoiceList invoiceList){
        try {
            int i = listService.updateInvoiceList(invoiceList);
            if(i==0){
                return success();
            }else{
                return error("操作失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - updateInvoiceList - error");
            return error("操作失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:22
    * @Description: 分页查询- 发票主表信息。
    * @Param: [pageBean, invoiceList]
    * @return: java.util.Map
    */
    @RequestMapping("/getInvoiceListPage")
    public Map getInvoiceListPage(PageBean<InvoiceList> pageBean, InvoiceList invoiceList){
        try {
            pageBean = listService.getInvoiceListPage(pageBean,invoiceList);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - getInvoiceListPage - error");
            return error("操作失败！");
        }
    }


    /*********************************************************** 发票附表 invoice_detail ********************************************************************/


    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:24
    * @Description: 保存 发票附表-
    * @Param: [detail]
    * @return: java.util.Map
    */
    @RequestMapping("/saveInvoiceDetail")
    public Map saveInvoiceDetail(InvoiceDetail detail){
        try {
            loginUser = getLoginUser();
            detail.setUserId(loginUser.getUserId());
            detail.setCompanyId(loginUser.getCompanyId());
            int i = listService.saveInvoiceDetail(detail);
            if(i==1){
                return success();
            }else{
                return error("操作失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - saveInvoiceDetail - error");
            return error("操作失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:29
    * @Description: 根据主键id 查询发票附表信息。
    * @Param: [detailId]
    * @return: java.util.Map
    */
    @RequestMapping("/getInvoiceDetailById")
    public Map getInvoiceDetailById(String detailId){
        try {
            InvoiceDetail detail = listService.getInvoiceDetailById(detailId);
            return showSucc(detail);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - getInvoiceDetailById - error");
            return error("操作失败！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:31
    * @Description: 分页查询， 发票主表下的发票详情
    * @Param: [pageBean, detail]
    * @return: java.util.Map
    */
    @RequestMapping("/getInvoiceDetailPageByListId")
    public Map getInvoiceDetailPageByListId(PageBean<InvoiceDetail> pageBean,InvoiceDetail detail){
        try {
            pageBean = listService.getInvoiceDetailPageByListId(pageBean,detail);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - getInvoiceDetailPageByListId - error");
            return error("操作失败！");
        }
    }


    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:34
    * @Description: 查询全部的发票详情，
    * @Param: [pageBean, detail]
    * @return: java.util.Map
    */
    @RequestMapping("/getInvoiceDetailPage")
    public Map getInvoiceDetailPage(PageBean<InvoiceDetail> pageBean,InvoiceDetail detail){
        try {
            pageBean = listService.getInvoiceDetailPageByListId(pageBean,detail);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InvoiceController - getInvoiceDetailPage - error");
            return error("操作失败！");
        }
    }
}
