package com.jamesZhan.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 16255 on 2017/11/2.
 */
public class OriginalsrcportEntity {
    private int originalSrcPortId;
    private Date startTime;
    private Date endTime;
    private String statisticsValue;
    private int count;

    public int getOriginalSrcPortId() {
        return originalSrcPortId;
    }

    public void setOriginalSrcPortId(int originalSrcPortId) {
        this.originalSrcPortId = originalSrcPortId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatisticsValue() {
        return statisticsValue;
    }

    public void setStatisticsValue(String statisticsValue) {
        this.statisticsValue = statisticsValue;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OriginalsrcportEntity that = (OriginalsrcportEntity) o;

        if (originalSrcPortId != that.originalSrcPortId) return false;
        if (count != that.count) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (statisticsValue != null ? !statisticsValue.equals(that.statisticsValue) : that.statisticsValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = originalSrcPortId;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (statisticsValue != null ? statisticsValue.hashCode() : 0);
        result = 31 * result + count;
        return result;
    }
    
    @Override
    public String toString(){
        return "OriginalSrcPort{" +
                "originalSrcPortId=" + originalSrcPortId +
                ",startTime=" + startTime +
                ",endTime=" + endTime +
                ",statisticsValue=" + statisticsValue +
                ",count=" + count +
                "}";
    }
}
