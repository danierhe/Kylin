package com.example.demo.weddings_funerals.controller;

import com.example.demo.base.BaseController;
import com.example.demo.weddings_funerals.pojo.WeddingsFunerals;
import com.example.demo.weddings_funerals.pojo.WeddingsFuneralsDetails;
import com.example.demo.weddings_funerals.service.WeddingsFuneralsService;
import commons.PageBean;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
 * @date 2020-02-21-02-21 20:51
 */
@RestController
@RequestMapping("/weddings-funerals")
public class WeddingsFuneralsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(WeddingsFuneralsController.class);

    @Autowired
    private WeddingsFuneralsService weddingsFuneralsService;

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 08:58
    * @Description: 保存主表信息
    * @Param: [weddingsFunerals]
    * @return: java.util.Map
    */
    @RequestMapping("/saveWeddingsFunerals")
    public Map saveWeddingsFunerals(WeddingsFunerals weddingsFunerals){
        try {
            int i = weddingsFuneralsService.saveWeddingFuneral(weddingsFunerals);
            if(i==1){
                return success();
            }else{
                return error("保存失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - saveWeddingsFunerals - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 08:59
    * @Description: 根基id查询 详情
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/selectWeddingsFunerals")
    public Map selectWeddingsFunerals(String id){
        try {
            WeddingsFunerals weddingsFunerals = weddingsFuneralsService.getWeddingsFuneralsById(id);
            return showSucc(weddingsFunerals);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - selectWeddingsFunerals - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 09:01
    * @Description:  根据条件查询主表列表  - 分页
     * @Param: [pageBean, title, startTime, endTime]
    * @return: java.util.Map
    */
    @RequestMapping("/selectWeddingsFuneralsPage")
    public Map selectWeddingsFuneralsPage(PageBean<WeddingsFunerals> pageBean,String title,String startTime,String endTime){
        try {
            pageBean = weddingsFuneralsService.getWeddingsFuneralsPage(pageBean,title,startTime,endTime);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - selectWeddingsFuneralsPage - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 09:03
    * @Description:  删除   主表
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/deleteWeddingsFuneralsById")
    public Map deleteWeddingsFuneralsById(String id){
        try {
            weddingsFuneralsService.deleteWeddingsFuneralsById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - deleteWeddingsFuneralsById - error");
            return error("操作失败，请联系管理员！");
        }
    }



    /**********************************************************************************附表信息****************************************************************************/
    /**********************************************************************************附表信息****************************************************************************/
    /**********************************************************************************附表信息****************************************************************************/

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 09:05
    * @Description:  保存详情
    * @Param: [details]
    * @return: java.util.Map
    */
    @RequestMapping("/saveDetail")
    public Map saveDetail(WeddingsFuneralsDetails details){
        try {
            weddingsFuneralsService.saveDetails(details);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - saveDetail - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 09:07
    * @Description:  删除详情-
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/deteleDetailsById")
    public Map deteleDetailsById(String id){
        try {
            weddingsFuneralsService.deleteDetailById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - deteleDetailsById - error");
            return error("操作失败，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 09:08
    * @Description:  查询详情列表- 分页
    * @Param: [pageBean, parentId]
    * @return: java.util.Map
    */
    @RequestMapping("/getDetailPage")
    public Map getDetailPage(PageBean<WeddingsFuneralsDetails> pageBean,String parentId){
        try {
            pageBean = weddingsFuneralsService.getDetailsPage(pageBean,parentId);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - getDetailPage - error");
            return error("操作失败，请联系管理员！");
        }
    }


    /**
    * @Author: DanierHe
    * @Date: 2020-02-21 下午 09:14
    * @Description: 查询详情- 集合 list
    * @Param: [parentId]
    * @return: java.util.Map
    */
    @RequestMapping("/getDetailList")
    public Map getDetailList(String parentId){
        try {
            List<WeddingsFuneralsDetails> list = weddingsFuneralsService.getDetailsList(parentId);
            PageBean<WeddingsFuneralsDetails> pageBean = new PageBean<WeddingsFuneralsDetails>();
            pageBean.setPage(1);
            pageBean.setLimit(list.size());
            pageBean.setCount(list.size());
            pageBean.setData(list);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("WeddingsFuneralsController - getDetailList - error");
            return error("操作失败，请联系管理员！");
        }
    }







}
