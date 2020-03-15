package com.example.demo.base;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-17-12-17 18:21
 */
@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/index")
    public String index2(){
        return "index";
    }

    //用户注册
    @RequestMapping("/users/user-register")
    public String userRegister(){
        return "users/user-register";
    }

    @RequestMapping("/work")
    public String work(){
        return "work";
    }


    //轮播图设置页面
    @RequestMapping("/carouselManager")
    public String carouselManager(){
        return "admin/carousel/carousel-manager";
    }



    // 发票列表页
    @RequestMapping("/invoice/to-invoice-list-page")
    public String toInvoiceListPage(){
        return "invoice/invoice-list-page";
    }


    //新增发票
    @RequestMapping("/invoice/to-invoice-list-add")
    public String toAddInvoiceList(){
        return "invoice/invoice-list-add";
    }

    //编辑发票
    @RequestMapping("/invoice/to-invoice-list-update")
    public String toUpdateInvoiceList(Model model,String oldListId){
        model.addAttribute("oldListId",oldListId);
        return "invoice/invoice-list-add";
    }

    // 百科搜索页面
    @RequestMapping("/cyclopedias/to-cyclopedias-search-page")
    public String toCyclopediasSearchPage(){
        return "cyclopedias/cyclopedias-search-page";
    }
    //百科详情页面
    @RequestMapping("/cyclopedias/to-cyclopedias-search-detail")
    public String toCyclopediasSearchDetail(Model model,String id){
        model.addAttribute("id",id);
        return "cyclopedias/cyclopedias-search-detail";
    }
    // 百科创建页面
    @RequestMapping("/cyclopedias/to-cyclopedias-search-create")
    public String toCyclopediasSearchCreate(){
        return "cyclopedias/cyclopedias-search-create";
    }


    /*
    *  日常开销
    * */
    //列表页
    @RequestMapping("/daily_cost/toDailyCosPage")
    public String toDailyCosPage(){
        return "daily_cost/daily-cost-page";
    }


    /************************************************** 礼单 *****************************************************/
    //礼单- 列表页
    @RequestMapping("/weddings-funerals/toWeddingsFuneralsPage")
    public String toWeddingsFuneralsPage(){
        return "weddingsFunerals/weddings-funerals-page";
    }
    //详情页
    @RequestMapping("/weddings-funerals/toWeddingsFuneralsDetails")
    public String toWeddingsFuneralsDetails(Model model,String id){
        model.addAttribute("parentId",id);
        return "weddingsFunerals/weddings-funerals-details";
    }

    /************************************************** 游戏娱乐 *****************************************************/

    //列表页
    @RequestMapping("/games/toGamesPage")
    public String toGamesPage(){
        return "games/games-page";
    }
    //详情页
    @RequestMapping("/games/toGamesDetails")
    public String toGamesDetails(Model model,String gamesId){
        model.addAttribute("gamesId",gamesId);
        return "games/games-details";
    }

}
