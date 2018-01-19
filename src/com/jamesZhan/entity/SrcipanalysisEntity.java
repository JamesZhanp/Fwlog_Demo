package com.jamesZhan.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 16255 on 2017/11/2.
 */
public class SrcipanalysisEntity {
    private int srcIpAnalysisId;
    private Date startTime;
    private Date endTime;
    private String statisticsValue;
    private double average;
    private double variance;

    public int getSrcIpAnalysisId() {
        return srcIpAnalysisId;
    }

    public void setSrcIpAnalysisId(int srcIpAnalysisId) {
        this.srcIpAnalysisId = srcIpAnalysisId;
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

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SrcipanalysisEntity that = (SrcipanalysisEntity) o;

        if (srcIpAnalysisId != that.srcIpAnalysisId) return false;
        if (Double.compare(that.average, average) != 0) return false;
        if (Double.compare(that.variance, variance) != 0) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (statisticsValue != null ? !statisticsValue.equals(that.statisticsValue) : that.statisticsValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = srcIpAnalysisId;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (statisticsValue != null ? statisticsValue.hashCode() : 0);
        temp = Double.doubleToLongBits(average);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(variance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString(){
        return "SrcIpAnalysis{" +
                "srcIpAnalysisId= " + srcIpAnalysisId +
                ",startTime=" + startTime +
                ",endTime=" + endTime +
                ",statisticsValue=" + statisticsValue +
                ",average=" + average +
                ",variance=" + variance +
                "}";
    }
}
