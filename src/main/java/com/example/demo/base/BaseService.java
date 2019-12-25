package com.example.demo.base;

import commons.IdWorker;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @author DanierHe
 * @description
 * @date 2019-12-20-12-20 14:46
 */
public class BaseService {
    public static final String USABLE_STATUS="1";//可以
    public static final String DELETE_STATUS="0";//删除，不可用

    public String getKey(String key){
        try {
            IdWorker idWorker = IdWorker.getFlowIdWorkerInstance();
            return key+idWorker.nextId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }

}
