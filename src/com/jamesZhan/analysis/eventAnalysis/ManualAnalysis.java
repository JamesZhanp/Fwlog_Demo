package com.jamesZhan.analysis.eventAnalysis;

import com.jamesZhan.dao.EventDao;
import com.jamesZhan.entity.EventEntity;
import org.junit.Test;

import java.util.List;

/**
 * Created by 16255 on 2017/11/8.
 * this is for the manual analysis to judge the event analysis we get
 * 对于敏感端口类的事件：
 * 首先对于特定的端口进行记录
 * 若存在，在原始日志数据当中进行寻找，
 */
public class ManualAnalysis {
    private EventDao eventDao = new EventDao();
    @Test
    //人工分析函数
    public void ManualAnalysis(){
        List<EventEntity> eventList = eventDao.getAllEvent();
//        System.out.println(eventList.size());
        for (int i = 0 ; i < eventList.size() ; i++){

        }
    }
}
