package com.example.demo.invoice.mapper;

import com.example.demo.invoice.pojo.InvoiceDetail;
import com.example.demo.invoice.pojo.InvoiceDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface InvoiceDetailMapper {
    long countByExample(InvoiceDetailExample example);

    int deleteByExample(InvoiceDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(InvoiceDetail record);

    int insertSelective(InvoiceDetail record);

    List<InvoiceDetail> selectByExample(InvoiceDetailExample example);

    InvoiceDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") InvoiceDetail record, @Param("example") InvoiceDetailExample example);

    int updateByExample(@Param("record") InvoiceDetail record, @Param("example") InvoiceDetailExample example);

    int updateByPrimaryKeySelective(InvoiceDetail record);

    int updateByPrimaryKey(InvoiceDetail record);

    //查询 分页
    List<InvoiceDetail> getInvoiceDetailPage(@Param("detail") InvoiceDetail detail);

    @Select("select * from invoice_detail where status=1 and list_id =#{listId}")
    List<InvoiceDetail> getDetailListByListId(@Param("listId") String listId);

    //查询金额
    @Select("select sum(invoice_value) as invoiceValue from invoice_detail where status=1 and list_id=#{listId}")
    InvoiceDetail getTotalValueByListId(@Param("listId")String listId);

    //修改list_id
    @Update("update invoice_detail set list_id=#{newId} where list_id=#{listId}")
    void updateByListId(@Param("listId") String listId,@Param("newId") String newId);



}