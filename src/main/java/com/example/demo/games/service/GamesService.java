package com.example.demo.games.service;

import com.example.demo.base.BaseService;
import com.example.demo.games.mapper.GamesMapper;
import com.example.demo.games.mapper.GamesRecordMapper;
import com.example.demo.games.pojo.Games;
import com.example.demo.games.pojo.GamesExample;
import com.example.demo.games.pojo.GamesRecord;
import com.example.demo.games.pojo.GamesRecordExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.DateUtils;
import commons.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-23-02-23 00:26
 */
@Service
@Transactional
public class GamesService extends BaseService {

    @Autowired
    private GamesMapper gamesMapper;
    @Autowired
    private GamesRecordMapper recordMapper;

    //新增游戏
    public int saveGames(Games games)throws Exception{
        games.setId(getKey("games"));
        games.setCreateTime(DateUtils.getYmdhms());
        games.setStatus(USABLE_STATUS);
        return gamesMapper.insert(games);
    }

    //删除游戏
    public int deleteGamesById(String id) throws Exception{
        Games games = gamesMapper.selectByPrimaryKey(id);
        games.setStatus(DELETE_STATUS);
        games.setUpdateTime(DateUtils.getYmdhms());
        return gamesMapper.updateByPrimaryKey(games);
    }


    //查询游戏
    public Games getGamesById(String id) throws Exception{
        return gamesMapper.selectByPrimaryKey(id);
    }

    //查询游戏-分页
    public PageBean<Games> getGamesPage(PageBean<Games> pageBean,Games games) throws Exception{
        GamesExample example = new GamesExample();
        GamesExample.Criteria criteria = example.createCriteria();
        if(games!=null){
            //游戏名称
            if(StringUtils.isNotBlank(games.getGameName())){
                criteria.andGameNameLike("%"+games.getGameName()+"%");
            }
            //角色名称
            if(StringUtils.isNotBlank(games.getRoleName())){
                criteria.andRoleNameLike("%"+games.getRoleName()+"%");
            }
            //游戏账号
            if(StringUtils.isNotBlank(games.getGameAccount())){
                criteria.andGameAccountLike("%"+games.getGameAccount()+"%");
            }
            //密码
            //服务器-区
        }
        criteria.andStatusEqualTo("1");
        example.setOrderByClause("create_time desc ");

        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<Games> list = gamesMapper.selectByExample(example);
        PageInfo<Games> pageInfo = new PageInfo<>(list);
        pageBean.setCount((int) pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }

    /****************************************************************  游戏记录 **************************************************************/
    /****************************************************************  游戏记录 **************************************************************/
    /****************************************************************  游戏记录 **************************************************************/

    //添加记录内容
    public void saveGamesRecord(GamesRecord gamesRecord)throws Exception{
        gamesRecord.setId(getKey("games-record"));
        gamesRecord.setCreateTime(DateUtils.getYmdhms());
        gamesRecord.setStatus(USABLE_STATUS);
        recordMapper.insert(gamesRecord);
    }
    //删除
    public void deleteGamesRecordById(String id) throws Exception{
        GamesRecord gamesRecord = recordMapper.selectByPrimaryKey(id);
        gamesRecord.setStatus(DELETE_STATUS);
        gamesRecord.setUpdateTime(DateUtils.getYmdhms());
        recordMapper.updateByPrimaryKey(gamesRecord);
    }

    //查询-记录
    public GamesRecord selectGamesRecord(String id)throws Exception{
        return recordMapper.selectByPrimaryKey(id);
    }

    //查询列表-page
    public PageBean<GamesRecord> getGamesRecordPage(PageBean<GamesRecord> pageBean,String gamesId)throws Exception{
        GamesRecordExample example = new GamesRecordExample();
        GamesRecordExample.Criteria criteria = example.createCriteria();
        criteria.andGamesIdEqualTo(gamesId);
        criteria.andStatusEqualTo("1");
        example.setOrderByClause(" create_time desc ");
        PageHelper.startPage(pageBean.getPage(),pageBean.getLimit());
        List<GamesRecord> list = recordMapper.selectByExample(example);
        PageInfo<GamesRecord> pageInfo = new PageInfo<>(list);
        pageBean.setCount((int) pageInfo.getTotal());
        pageBean.setData(list);
        return pageBean;
    }









}
