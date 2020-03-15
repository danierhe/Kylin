package com.example.demo.invoice.service;

import com.example.demo.base.BaseService;
import com.example.demo.invoice.mapper.InvoiceDetailMapper;
import com.example.demo.invoice.mapper.InvoiceListMapper;
import com.example.demo.invoice.pojo.InvoiceDetail;
import com.example.demo.invoice.pojo.InvoiceList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.DateUtils;
import commons.PageBean;
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
 * @date 2019-12-25-12-25 13:54
 */
@Service
@Transactional
public class InvoiceListService extends BaseService {
    private Logger logger = LoggerFactory.getLogger(InvoiceListService.class);

    @Autowired
    private InvoiceListMapper listMapper;
    @Autowired
    private InvoiceDetailMapper detailMapper;

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 02:17
    * @Description: 保存主表信息-
    * @Param: [invoiceList]
    * @return: int
    */
    public int saveInvoiceList(InvoiceList invoiceList) throws Exception{
        String listId = invoiceList.getId();
        invoiceList.setId(getKey("invoiceLists"));
        invoiceList.setStatus(USABLE_STATUS);
        invoiceList.setCreateTime(DateUtils.getYmdhms());
        //求总报销金额
        InvoiceDetail detail = detailMapper.getTotalValueByListId(listId);
        invoiceList.setTotalValue(detail.getInvoiceValue());
        //修改detail
        detailMapper.updateByListId(listId,invoiceList.getId());

        return listMapper.insert(invoiceList);
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 02:47
    * @Description: 根据主键查询主表信息
    * @Param: [id]
    * @return: com.example.demo.invoice.pojo.InvoiceList
    */
    public InvoiceList getInvoiceListById(String id) throws Exception{
        return listMapper.selectByPrimaryKey(id);
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 02:49
    * @Description: 更新发票主表信息
    * @Param: [invoiceList]
    * @return: int
    */
    public int updateInvoiceList(InvoiceList invoiceList) throws Exception{
        invoiceList.setUpdateTime(DateUtils.getYmdhms());
        return listMapper.updateByPrimaryKey(invoiceList);
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 02:35
    * @Description: 分页查询-发票主表
    * @Param: [pageBean, invoiceList]
    * @return: commons.PageBean<com.example.demo.invoice.pojo.InvoiceList>
    */
    public PageBean<InvoiceList> getInvoiceListPage(PageBean<InvoiceList> pageBean,InvoiceList invoiceList) throws Exception{
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<InvoiceList> list = listMapper.getInvoiceListPage(invoiceList);
        PageInfo<InvoiceList> pageInfo = new PageInfo<>(list);
        pageBean.setCount((int) pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }


    /*  ****************************************  发票附表  invoice_detail  *******************************************   */

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 02:53
    * @Description: 保存发票附表信息
    * @Param: [detail]
    * @return: int
    */
    public int saveInvoiceDetail(InvoiceDetail detail) throws Exception{
        detail.setId(getKey("invoiceDetails"));
        detail.setCreateTime(DateUtils.getYmdhms());
        detail.setStatus(USABLE_STATUS);
        return  detailMapper.insert(detail);
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 02:54
    * @Description: 根据主键id，查询附表详情
    * @Param: [id]
    * @return: com.example.demo.invoice.pojo.InvoiceDetail
    */
    public InvoiceDetail getInvoiceDetailById(String id) throws Exception{
        return detailMapper.selectByPrimaryKey(id);
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 03:07
    * @Description:
    * @Param: [pageBean, detail]
    * @return: commons.PageBean<com.example.demo.invoice.pojo.InvoiceDetail>
    */
    public PageBean<InvoiceDetail> getInvoiceDetailPageByListId(PageBean<InvoiceDetail> pageBean,InvoiceDetail detail)throws Exception{
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<InvoiceDetail> list = detailMapper.getInvoiceDetailPage(detail);
        PageInfo<InvoiceDetail> pageInfo = new PageInfo<>(list);
        pageBean.setCount((int) pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }

    /**
    * @Author: DanierHe
    * @Date: 2019-12-25 下午 09:08
    * @Description: 根据listId，查询明细
    * @Param: [listId]
    * @return: java.util.List<com.example.demo.invoice.pojo.InvoiceDetail>
    */
    public List<InvoiceDetail> getDetailListByListId(String listId) throws Exception{
        List<InvoiceDetail> list = detailMapper.getDetailListByListId(listId);
        return list;
    }
}
