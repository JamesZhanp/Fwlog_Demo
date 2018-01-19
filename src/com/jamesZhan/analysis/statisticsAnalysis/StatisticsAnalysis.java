package com.jamesZhan.analysis.statisticsAnalysis;

import com.jamesZhan.dao.PublicFunctionDao;
import com.jamesZhan.entity.*;
import com.jamesZhan.utils.VarianceUtil;
import sun.invoke.util.VerifyAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 16255 on 2017/11/4.
 * @author jamesZhan
 */
public class StatisticsAnalysis {
    private static Date lastTime = null;
    private static Date nowTime = null;
    private static PublicFunctionDao publicFunctionDao = new PublicFunctionDao();
    public static void analysis(){
        List<OriginalsrcipEntity> list = publicFunctionDao.getAllMessage(0);
        List<OriginalsrcportEntity> list1 = publicFunctionDao.getAllMessage(1);
        List<OriginaldestipEntity> list2 = publicFunctionDao.getAllMessage(2);
        List<OriginaldestportEntity> list3 = publicFunctionDao.getAllMessage(3);

        int srcIpListLength = list.size();
        int srcPortListLength = list1.size();
        int destIpLength = list2.size();
        int destPortLength = list3.size();
//        srcIp analysis
        for (int i = 0 ; i < srcIpListLength ; i++){
            List<OriginalsrcipEntity> everySrcIpList = publicFunctionDao.getDetailForGroup(list.get(i).getStatisticsValue(),0);
            int everySrcIpListLength = everySrcIpList.size();
            ArrayList count = new ArrayList();
            if(everySrcIpListLength >= 2){
                lastTime = everySrcIpList.get(0).getStartTime();
                for(int j = 0 ; j < everySrcIpListLength ; j++){
                    //链表的最后一个事件分开考虑，当与前面发生的属于同一事件时，我们加入考虑，否则抛弃
                    if(j == everySrcIpListLength - 1){
                        //if the final thing is the same access thing
                        if(everySrcIpList.get(j).getStartTime().getTime() == everySrcIpList.get(j - 1).getEndTime().getTime()){
                            count.add(everySrcIpList.get(j).getCount());
                            nowTime = everySrcIpList.get(j).getEndTime();
                            SrcipanalysisEntity srcipanalysisEntity = new SrcipanalysisEntity();
                            srcipanalysisEntity.setStartTime(lastTime);
                            srcipanalysisEntity.setEndTime(nowTime);
                            srcipanalysisEntity.setStatisticsValue(everySrcIpList.get(j).getStatisticsValue());
                            srcipanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            srcipanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if(nowTime.getTime() - lastTime.getTime() > 1000){
                                publicFunctionDao.insert(srcipanalysisEntity);
                            }
                            count.clear();
                        }//if
                    }
                    else{
                        count.add(everySrcIpList.get(j).getCount());
                        if(everySrcIpList.get(j + 1).getStartTime().getTime() > everySrcIpList.get(j).getEndTime().getTime()){
                            nowTime = everySrcIpList.get(j).getEndTime();
                            SrcipanalysisEntity srcipanalysisEntity = new SrcipanalysisEntity();
                            srcipanalysisEntity.setStartTime(lastTime);
                            srcipanalysisEntity.setEndTime(nowTime);
                            srcipanalysisEntity.setStatisticsValue(everySrcIpList.get(j).getStatisticsValue());
                            srcipanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            srcipanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if (nowTime.getTime() - lastTime.getTime() > 1000){
                                publicFunctionDao.insert(srcipanalysisEntity);
                            }
                            lastTime = everySrcIpList.get(j + 1).getStartTime();
                            count.clear();
                        }//if
                    }//if
                }//for
            }//if
        }//for
//        srcPort analysis
        for (int i = 0 ; i < srcPortListLength ; i++){
            List<OriginalsrcportEntity> everySrcPortList = publicFunctionDao.getDetailForGroup(list1.get(i).getStatisticsValue(),1);
            int everySrcPortListLength = everySrcPortList.size();
            ArrayList count = new ArrayList();
            if(everySrcPortListLength >= 2){
                lastTime = everySrcPortList.get(0).getStartTime();
                for(int j = 0 ; j < everySrcPortListLength ; j++){
                    if(j == everySrcPortListLength - 1){
                        if(everySrcPortList.get(j).getStartTime().getTime() ==
                                everySrcPortList.get(j - 1).getEndTime().getTime()){
                            count.add(everySrcPortList.get(j).getCount());
                            SrcportanalysisEntity srcportanalysisEntity = new SrcportanalysisEntity();
                            nowTime = everySrcPortList.get(j).getStartTime();
                            srcportanalysisEntity.setStarttime(lastTime);
                            srcportanalysisEntity.setEndtime(nowTime);
                            srcportanalysisEntity.setStatisticsvalue(everySrcPortList.get(j).getStatisticsValue());
                            srcportanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            srcportanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if(nowTime.getTime() - lastTime.getTime() > 1000){
                                publicFunctionDao.insert(srcportanalysisEntity);
                            }
                            count.clear();
                        }
                    }else{
                        count.add(everySrcPortList.get(j).getCount());
                        if(everySrcPortList.get(j).getEndTime().getTime() <
                                everySrcPortList.get(j + 1).getStartTime().getTime()){
                            SrcportanalysisEntity srcportanalysisEntity = new SrcportanalysisEntity();
                            nowTime = everySrcPortList.get(j).getEndTime();
                            srcportanalysisEntity.setStarttime(lastTime);
                            srcportanalysisEntity.setEndtime(nowTime);
                            srcportanalysisEntity.setStatisticsvalue(everySrcPortList.get(j).getStatisticsValue());
                            srcportanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            srcportanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if(nowTime.getTime() - lastTime.getTime() >1000){
                                publicFunctionDao.insert(srcportanalysisEntity);
                            }
                            lastTime = everySrcPortList.get(j + 1).getStartTime();
                            count.clear();
                        }
                    }
                }
            }
        }
//        destPort analysis
        for (int i = 0 ; i < destIpLength ; i++){
            List<OriginaldestipEntity> everyDestIp = publicFunctionDao.getDetailForGroup(list2.get(i).getStatisticsValue(),2);
            int everyDestIpLength = everyDestIp.size();
            ArrayList count = new ArrayList();
            if (everyDestIpLength >= 2){
                lastTime = everyDestIp.get(0).getStartTime();
                for (int j = 0 ; j < everyDestIpLength ; j++){
                    if (j == everyDestIpLength - 1){
                        if (everyDestIp.get(j).getStartTime().getTime() == everyDestIp.get(j - 1).getEndTime().getTime()){
                            count.add(everyDestIp.get(j).getCount());
                            nowTime = everyDestIp.get(j).getEndTime();
                            DestipanalysisEntity destipanalysisEntity = new DestipanalysisEntity();
                            destipanalysisEntity.setStarttime(lastTime);
                            destipanalysisEntity.setEndtime(nowTime);
                            destipanalysisEntity.setStatisticsvalue(everyDestIp.get(j).getStatisticsValue());
                            destipanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            destipanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if (nowTime.getTime() - lastTime.getTime() > 1000){
                                publicFunctionDao.insert(destipanalysisEntity);
                            }
                            count.clear();
                        }
                    }else{
                        count.add(everyDestIp.get(j).getCount());
                        if(everyDestIp.get(j).getEndTime().getTime() < everyDestIp.get(j + 1).getStartTime().getTime()){
                            nowTime = everyDestIp.get(j).getEndTime();
                            DestipanalysisEntity destipanalysisEntity = new DestipanalysisEntity();
                            destipanalysisEntity.setStarttime(lastTime);
                            destipanalysisEntity.setEndtime(nowTime);
                            destipanalysisEntity.setStatisticsvalue(everyDestIp.get(j).getStatisticsValue());
                            destipanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            destipanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if (nowTime.getTime() - lastTime.getTime() > 1000){
                                publicFunctionDao.insert(destipanalysisEntity);
                            }
                            lastTime = everyDestIp.get(j + 1).getStartTime();
                            count.clear();
                        }
                    }
                }
            }
        }
//        destport analysis
        for (int i = 0 ; i < destPortLength ; i++){
            List<OriginaldestportEntity> everyDestPortList = publicFunctionDao.getDetailForGroup(list3.get(i).getStatisticsValue(),3);
            int everyDestPortListLength = everyDestPortList.size();
            ArrayList count = new ArrayList();
            if(everyDestPortListLength >= 2){
                lastTime = everyDestPortList.get(0).getStartTime();
                for (int j = 0 ; j < everyDestPortListLength ; j++){
                    if(j == everyDestPortListLength - 1){
                        if(everyDestPortList.get(j).getStartTime().getTime() == everyDestPortList.get(j - 1).getEndTime().getTime()){
                            count.add(everyDestPortList.get(j).getCount());
                            nowTime = everyDestPortList.get(j).getEndTime();
                            DestportanalysisEntity destportanalysisEntity = new DestportanalysisEntity();
                            destportanalysisEntity.setStarttime(lastTime);
                            destportanalysisEntity.setEndtime(nowTime);
                            destportanalysisEntity.setStatictisvalue(everyDestPortList.get(j).getStatisticsValue());
                            destportanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            destportanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if(nowTime.getTime() - lastTime.getTime() > 1000){
                                publicFunctionDao.insert(destportanalysisEntity);
                            }
                            count.clear();
                        }
                    }else{
                        count.add(everyDestPortList.get(j).getCount());
                        if(everyDestPortList.get(j).getEndTime().getTime() < everyDestPortList.get(j + 1).getStartTime().getTime()){
                            nowTime = everyDestPortList.get(j).getEndTime();
                            DestportanalysisEntity destportanalysisEntity = new DestportanalysisEntity();
                            destportanalysisEntity.setStarttime(lastTime);
                            destportanalysisEntity.setEndtime(nowTime);
                            destportanalysisEntity.setStatictisvalue(everyDestPortList.get(j).getStatisticsValue());
                            destportanalysisEntity.setAverage(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getAverage(count)));
                            destportanalysisEntity.setVariance(VarianceUtil.keepTwoDecimalPlaces(VarianceUtil.getVariance(count)));
                            if(nowTime.getTime() - lastTime.getTime() > 1000){
                                publicFunctionDao.insert(destportanalysisEntity);
                            }
                            lastTime = everyDestPortList.get(j + 1).getStartTime();
                            count.clear();
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        analysis();
    }
}
