package com.jamesZhan.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 16255 on 2017/11/2.
 */
public class DestipanalysisEntity {
    private int destipId;
    private Date starttime;
    private Date endtime;
    private String statisticsvalue;
    private double average;
    private double variance;

    public int getDestipId() {
        return destipId;
    }

    public void setDestipId(int destipId) {
        this.destipId = destipId;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getStatisticsvalue() {
        return statisticsvalue;
    }

    public void setStatisticsvalue(String statisticsvalue) {
        this.statisticsvalue = statisticsvalue;
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

        DestipanalysisEntity that = (DestipanalysisEntity) o;

        if (destipId != that.destipId) return false;
        if (Double.compare(that.average, average) != 0) return false;
        if (Double.compare(that.variance, variance) != 0) return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;
        if (statisticsvalue != null ? !statisticsvalue.equals(that.statisticsvalue) : that.statisticsvalue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = destipId;
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (statisticsvalue != null ? statisticsvalue.hashCode() : 0);
        temp = Double.doubleToLongBits(average);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(variance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString(){
        return "DestIp = {" +
                "id=" + destipId +
                ",starttime=" + starttime +
                ",endtime=" + endtime +
                ",statictisvalue=" + statisticsvalue +
                ",average=" + average +
                ",variance=" + variance +
                "}";
    }
}
