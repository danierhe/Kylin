package com.example.demo.invoice.mapper;

import com.example.demo.invoice.pojo.InvoiceList;
import com.example.demo.invoice.pojo.InvoiceListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceListMapper {
    long countByExample(InvoiceListExample example);

    int deleteByExample(InvoiceListExample example);

    int deleteByPrimaryKey(String id);

    int insert(InvoiceList record);

    int insertSelective(InvoiceList record);

    List<InvoiceList> selectByExample(InvoiceListExample example);

    InvoiceList selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InvoiceList record, @Param("example") InvoiceListExample example);

    int updateByExample(@Param("record") InvoiceList record, @Param("example") InvoiceListExample example);

    int updateByPrimaryKeySelective(InvoiceList record);

    int updateByPrimaryKey(InvoiceList record);

    //分页查询   发票主表
    List<InvoiceList> getInvoiceListPage(@Param("invoiceList") InvoiceList invoiceList);
}