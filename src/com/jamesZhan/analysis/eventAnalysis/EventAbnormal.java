package com.jamesZhan.analysis.eventAnalysis;

import com.jamesZhan.dao.AnalysisPublicDao;
import com.jamesZhan.dao.DestPortAnalysisDao;
import com.jamesZhan.dao.PublicFunctionDao;
import com.jamesZhan.dao.SensitivePortDao;
import com.jamesZhan.entity.*;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by 16255 on 2017/11/5.
 */
public class EventAbnormal {
    private static double DEVIATION = 9.98;
    private static double VARIANCE_INTERVAL = 50.00;
    private static Date lastTime = null;
    private static Date nowTime = null;
    private static PublicFunctionDao publicFunctionDao = new PublicFunctionDao();
    private static SensitivePortDao sensitivePortDao = new SensitivePortDao();
    private static DestPortAnalysisDao destPortAnalysisDao = new DestPortAnalysisDao();
    private static AnalysisPublicDao analysisPublicDao = new AnalysisPublicDao();
    public static void judgeSensitivePort(){
//        get the all sensitive port
        List<SensitiveportEntity> sensitivePort = sensitivePortDao.FindAll();
//        get the list of the destport which is accessed
        List<OriginaldestportEntity> destPortList = destPortAnalysisDao.getAllDestPort();
        for (int i = 0 ; i < sensitivePort.size() ; i++){
            for (int j = 0 ; j < destPortList.size() ; j++){
                int sPort = sensitivePort.get(i).getPort();
                int destPort = Integer.parseInt(destPortList.get(j).getStatisticsValue());
//                find the sensitivePort and get the access history
                if (sPort == destPort){
                    List<OriginaldestportEntity> eachPortAccessHistory = destPortAnalysisDao.getAccessList(destPortList.get(j).getStatisticsValue());
//                    System.out.println(eachPortAccessHistory.size());
                    lastTime = eachPortAccessHistory.get(0).getStartTime();
                    EventEntity eventEntity = new EventEntity();
                    if(eachPortAccessHistory.size() == 1){
                        eventEntity.setStartTime(eachPortAccessHistory.get(0).getStartTime());
                        eventEntity.setLastTime(eachPortAccessHistory.get(0).getEndTime());
                        eventEntity.setStatisticsValue(eachPortAccessHistory.get(0).getStatisticsValue());
                        eventEntity.setType(String.valueOf(EventType.SensitivePortAccess));
                        eventEntity.setIsFinished(true);
                        publicFunctionDao.insert(eventEntity);
                    }else {
                        for(int k = 0 ; k < eachPortAccessHistory.size() ; k++){
//                            判断最后一条是否与前一条为相同事件
                            if(k == eachPortAccessHistory.size() - 1){
                                if(eachPortAccessHistory.get(k).getStartTime().getTime() ==
                                        eachPortAccessHistory.get(k - 1).getEndTime().getTime()){
                                    nowTime = eachPortAccessHistory.get(k).getEndTime();
                                    eventEntity.setIsFinished(true);
                                    eventEntity.setType(String.valueOf(EventType.SensitivePortAccess));
                                    eventEntity.setStatisticsValue(eachPortAccessHistory.get(k).getStatisticsValue());
                                    eventEntity.setStartTime(lastTime);
                                    eventEntity.setLastTime(nowTime);
                                    publicFunctionDao.insert(eventEntity);
                                }else{
                                    nowTime = eachPortAccessHistory.get(k).getEndTime();
                                    nowTime = eachPortAccessHistory.get(k).getEndTime();
                                    eventEntity.setIsFinished(true);
                                    eventEntity.setType(String.valueOf(EventType.SensitivePortAccess));
                                    eventEntity.setStatisticsValue(eachPortAccessHistory.get(k).getStatisticsValue());
                                    eventEntity.setStartTime(lastTime);
                                    eventEntity.setLastTime(nowTime);
                                    publicFunctionDao.insert(eventEntity);
                                }
                            }else{
                                if(eachPortAccessHistory.get(k).getEndTime().getTime() < eachPortAccessHistory.get(k + 1).getStartTime().getTime()){
                                    nowTime = eachPortAccessHistory.get(k).getEndTime();
                                    eventEntity.setStartTime(lastTime);
                                    eventEntity.setLastTime(nowTime);
                                    eventEntity.setStatisticsValue(eachPortAccessHistory.get(k).getStatisticsValue());
                                    eventEntity.setType(String.valueOf(EventType.SensitivePortAccess));
                                    eventEntity.setIsFinished(true);
                                    lastTime = eachPortAccessHistory.get(k + 1).getStartTime();
                                    publicFunctionDao.insert(eventEntity);
                                }
                            }
                        }
                    }
               }
            }
        }

    }

//    get of analysis result and use the threshold
    public static void judgeAccessHistory(){
        //get all analysis result and
        List<SrcipanalysisEntity> srcIpAnalysisList = analysisPublicDao.getAllTableMes(0);
        List<SrcportanalysisEntity> srcPortAnalysisList = analysisPublicDao.getAllTableMes(1);
        List<DestipanalysisEntity> destIpAnalysisList = analysisPublicDao.getAllTableMes(2);
        List<DestportanalysisEntity> destPortAnalysisList = analysisPublicDao.getAllTableMes(3);

//        System.out.println(srcIpAnalysisList.size());
//        System.out.println(srcPortAnalysisList.size());
//        System.out.println(destIpAnalysisList.size());
//        System.out.println(destPortAnalysisList.size());
        for(SrcipanalysisEntity e : srcIpAnalysisList){
            EventEntity eventEntity = new EventEntity();
            if(e.getVariance() > VARIANCE_INTERVAL + DEVIATION){
                eventEntity.setIsFinished(true);
                eventEntity.setType(String.valueOf(EventType.sameSrcAttack));
                eventEntity.setStartTime(e.getStartTime());
                eventEntity.setLastTime(e.getEndTime());
                eventEntity.setStatisticsValue(e.getStatisticsValue());
                publicFunctionDao.insert(eventEntity);
            }
        }
        for(SrcportanalysisEntity e : srcPortAnalysisList ){
            EventEntity eventEntity = new EventEntity();
            if(e.getVariance() > VARIANCE_INTERVAL + DEVIATION){
                eventEntity.setIsFinished(true);
                eventEntity.setType(String.valueOf(EventType.sameSrcAttack));
                eventEntity.setStartTime(e.getStarttime());
                eventEntity.setLastTime(e.getEndtime());
                eventEntity.setStatisticsValue(e.getStatisticsvalue());
                publicFunctionDao.insert(eventEntity);
            }
        }
        for(DestipanalysisEntity e : destIpAnalysisList){
            EventEntity eventEntity = new EventEntity();
            if(e.getVariance() > VARIANCE_INTERVAL + DEVIATION){
                eventEntity.setIsFinished(true);
                eventEntity.setType(String.valueOf(EventType.sameDestAttack));
                eventEntity.setStartTime(e.getStarttime());
                eventEntity.setLastTime(e.getEndtime());
                eventEntity.setStatisticsValue(e.getStatisticsvalue());
                publicFunctionDao.insert(eventEntity);
            }
        }
        for(DestportanalysisEntity e : destPortAnalysisList){
            EventEntity eventEntity = new EventEntity();
            if(e.getVariance() > VARIANCE_INTERVAL + DEVIATION){
                eventEntity.setIsFinished(true);
                eventEntity.setType(String.valueOf(EventType.sameDestAttack));
                eventEntity.setStartTime(e.getStarttime());
                eventEntity.setLastTime(e.getEndtime());
                eventEntity.setStatisticsValue(e.getStatictisvalue());
                publicFunctionDao.insert(eventEntity);
            }
        }
    }

    @Test
    public static void judge(){
        judgeSensitivePort();
        judgeAccessHistory();
    }

}
