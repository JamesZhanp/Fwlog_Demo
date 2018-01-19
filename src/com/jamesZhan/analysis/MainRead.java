package com.jamesZhan.analysis;

import com.jamesZhan.analysis.eventAnalysis.EventAbnormal;
import com.jamesZhan.analysis.preProcess.FileRead;
import com.jamesZhan.analysis.statisticsAnalysis.Statistics;
import com.jamesZhan.analysis.statisticsAnalysis.StatisticsAnalysis;
import com.jamesZhan.dao.*;
import com.jamesZhan.entity.FwloganalysisEntity;
import com.jamesZhan.entity.RawfwlogEntity;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by 16255 on 2017/11/3.
 */
public class MainRead {
    private List<FwloganalysisEntity> fwlogs = FileRead.fwlogs;
    private List<RawfwlogEntity> rawFwlogs = FileRead.rawFwlogs;
    private PublicFunctionDao publicFunctionDao = new PublicFunctionDao();
    private boolean flag = true;
    @Test
    private void analysis(){
        //read the log file
        FileRead.readFile(new File("E://log//log1.txt"));
        while(flag) {
            if (fwlogs.size() > 0) {
//            System.out.println(fwlogs.size());
//            System.out.println(rawFwlogs.size());
                //get the first message
                FwloganalysisEntity fwlog = fwlogs.get(0);
                RawfwlogEntity rawFwlog = rawFwlogs.get(0);

                //save the message into db
                publicFunctionDao.insert(fwlog);
                publicFunctionDao.insert(rawFwlog);
                try{
                    Statistics.analysis(fwlog);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //remove the first message
                fwlogs.remove(0);
                rawFwlogs.remove(0);
            } else {
                flag = false;
            }

        }
        //analysis the log file
        StatisticsAnalysis.analysis();

        //judge the event
        EventAbnormal.judge();
    }
    public static void main(String[] args){
//        analysis();
    }
}
