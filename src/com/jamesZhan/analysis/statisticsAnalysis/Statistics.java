package com.jamesZhan.analysis.statisticsAnalysis;

import com.jamesZhan.analysis.preProcess.ParseLogRegex;
import com.jamesZhan.dao.*;
import com.jamesZhan.entity.*;

import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.jamesZhan.analysis.preProcess.ParseLogRegex.ParseLog;

/**
 * Created by 16255 on 2017/11/3.
 * @author  jamesZhan
 */
public class Statistics {
    public static long MIN_INTERVAL = 1000;
    private static Date lastTime = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private static PublicFunctionDao publicFunctionDao = new PublicFunctionDao();

    //statistic the fwlog and insert or update into db
    public static void analysis(FwloganalysisEntity fwlog) throws Exception{
        if(MIN_INTERVAL % 1000 != 0){
            throw new Exception("the MIN_INTERVAL must be multiply of 1s");
        }
        Date nowTime = fwlog.getTimestamp();
        if(lastTime == null || nowTime.getTime() - lastTime.getTime() >= MIN_INTERVAL){
            lastTime = fwlog.getTimestamp();
        }
//        get the ip and port from fwlog
        String[] ipAndPort = new String[4];
        ipAndPort[0] = fwlog.getOriginalSrcIp();
        ipAndPort[1] = fwlog.getOriginalSrcPort();
        ipAndPort[2] = fwlog.getOriginalDestIp();
        ipAndPort[3] = fwlog.getOriginalDestPort();

//        find there is the same thing happened
        Integer[] ID = new Integer[4];
        for(int i = 0 ; i < 4 ; i++){
            ID[i] = publicFunctionDao.findID(ipAndPort[i],sdf.format(lastTime.getTime()),sdf.format(new Date(lastTime.getTime() + MIN_INTERVAL)),i);
        }
        for(int i = 0 ; i < 4 ; i++){
            if(ID[i] == null){
                if(i == 0){
                    OriginalsrcipEntity originalsrcipEntity = new OriginalsrcipEntity();
                    originalsrcipEntity.setCount(1);
                    originalsrcipEntity.setStatisticsValue(ipAndPort[0]);
                    originalsrcipEntity.setStartTime(new Timestamp(lastTime.getTime()));
                    originalsrcipEntity.setEndTime(new Timestamp(lastTime.getTime() + MIN_INTERVAL));
                    publicFunctionDao.insert(originalsrcipEntity);
                }
                if(i == 1){
                    OriginalsrcportEntity originalsrcportEntity = new OriginalsrcportEntity();
                    originalsrcportEntity.setCount(1);
                    originalsrcportEntity.setStatisticsValue(ipAndPort[1]);
                    originalsrcportEntity.setStartTime(new Timestamp(lastTime.getTime()));
                    originalsrcportEntity.setEndTime(new Timestamp(lastTime.getTime() + MIN_INTERVAL));
                    publicFunctionDao.insert(originalsrcportEntity);
                }
                if (i == 2){
                    OriginaldestipEntity originaldestipEntity = new OriginaldestipEntity();
                    originaldestipEntity.setCount(1);
                    originaldestipEntity.setStatisticsValue(ipAndPort[2]);
                    originaldestipEntity.setStartTime(new Timestamp(lastTime.getTime()));
                    originaldestipEntity.setEndTime(new Timestamp(lastTime.getTime() + MIN_INTERVAL));
                    publicFunctionDao.insert(originaldestipEntity);
                }
                if(i == 3){
                    OriginaldestportEntity originaldestportEntity = new OriginaldestportEntity();
                    originaldestportEntity.setCount(1);
                    originaldestportEntity.setStatisticsValue(ipAndPort[3]);
                    originaldestportEntity.setStartTime(new Timestamp(lastTime.getTime()));
                    originaldestportEntity.setEndTime(new Timestamp(lastTime.getTime() + MIN_INTERVAL));
                    publicFunctionDao.insert(originaldestportEntity);
                }
            }else{
                publicFunctionDao.update(ID[i],i);
            }
        }
    }
    public static void main(String[] args){
        String fwlog = "Aug  1 00:00:00 11.116.14.155 2016-07-31: 23:42:39 FW_internetDMZ_Main:root 03-011-069-0017 Notice Session N/A rep=1 | 匹配到访问策略FOR_SERVER， 原始地址：172.21.181.82(58073)->212.117.201.1(53)， 协议：17，  转换后地址：172.22.181.82(58173)->202.127.211.1(53)， 安全域：outside->server， 动作：允许。";

        try{
            analysis(ParseLog(fwlog));
        } catch (Exception e){
            e.printStackTrace();

        }
    }

}
