package com.example.demo.games.controller;

import com.example.demo.base.BaseController;
import com.example.demo.games.pojo.Games;
import com.example.demo.games.pojo.GamesRecord;
import com.example.demo.games.service.GamesService;
import commons.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-23-02-23 15:37
 */
@RestController
@RequestMapping("/games")
public class GamesController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(GamesController.class);

    @Autowired
    private GamesService gamesService;

    /***************************************  主表  ******************************************************/
    /***************************************  主表  ******************************************************/

    /**
    * @Author: DanierHe
    * @Date: 2020-02-23 下午 03:40
    * @Description: 保存
    * @Param: [games]
    * @return: java.util.Map
    */
    @RequestMapping("/saveGames")
    public Map saveGames(Games games){
        try {
            gamesService.saveGames(games);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("GamesController - saveGames - error");
            return error("系统错误，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-23 下午 03:43
    * @Description: 查询详情
    * @Param: [id]
    * @return: java.util.Map
    */
    @RequestMapping("/getGamesById")
    public Map getGamesById(String id){
        try {
            Games games = gamesService.getGamesById(id);
            return showSucc(games);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("GamesController - getGamesById - error");
            return error("系统错误，请联系管理员！");
        }
    }

    /**
    * @Author: DanierHe
    * @Date: 2020-02-23 下午 03:45
    * @Description:  查询分页
    * @Param: [pageBean, games]
    * @return: java.util.Map
    */
    @RequestMapping("/getGamesPage")
    public Map getGamesPage(PageBean<Games> pageBean, Games games){
        try {
            pageBean = gamesService.getGamesPage(pageBean,games);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("GamesController - getGamesPage - error");
            return error("系统错误，请联系管理员！");
        }
    }

    //删除游戏
    @RequestMapping("/deleteGamesById")
    public Map deleteGamesById(String id){
        try {
            gamesService.deleteGamesById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("GamesController - deleteGamesById - error");
            return error("系统错误，请联系管理员！");
        }
    }



    /***************************************  附表 ******************************************************/
    /***************************************  附表 ******************************************************/

    //保存附表
    @RequestMapping("/saveGamesRecord")
    public Map saveGamesRecord(GamesRecord gamesRecord){
        try {
            gamesService.saveGamesRecord(gamesRecord);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("GamesController - saveGamesRecord - error");
            return error("系统错误，请联系管理员！");
        }
    }

    //删除附表
    @RequestMapping("/deleteGamesRecordById")
    public Map deleteGamesRecordById(String id){
        try {
            gamesService.deleteGamesRecordById(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("GamesController - deleteGamesRecordById - error");
            return error("系统错误，请联系管理员！");
        }
    }

    //查询附表-分页
    @RequestMapping("/getGamesRecordPage")
    public Map getGamesRecordPage(PageBean<GamesRecord> pageBean,String gamesId){
        try {
            pageBean = gamesService.getGamesRecordPage(pageBean,gamesId);
            return showLayTale(pageBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("GamesController - getGamesRecordPage - error");
            return error("系统错误，请联系管理员！");
        }
    }







}

