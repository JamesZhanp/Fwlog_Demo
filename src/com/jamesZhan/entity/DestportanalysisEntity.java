package com.jamesZhan.entity;

import java.util.Date;

/**
 * Created by 16255 on 2017/11/2.
 */
public class DestportanalysisEntity {
    private int destportId;
    private Date starttime;
    private Date endtime;
    private String statictisvalue;
    private double average;
    private double variance;

    public int getDestportId() {
        return destportId;
    }

    public void setDestportId(int destportId) {
        this.destportId = destportId;
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

    public String getStatictisvalue() {
        return statictisvalue;
    }

    public void setStatictisvalue(String statictisvalue) {
        this.statictisvalue = statictisvalue;
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

        DestportanalysisEntity that = (DestportanalysisEntity) o;

        if (destportId != that.destportId) return false;
        if (Double.compare(that.average, average) != 0) return false;
        if (Double.compare(that.variance, variance) != 0) return false;
        if (starttime != null ? !starttime.equals(that.starttime) : that.starttime != null) return false;
        if (endtime != null ? !endtime.equals(that.endtime) : that.endtime != null) return false;
        if (statictisvalue != null ? !statictisvalue.equals(that.statictisvalue) : that.statictisvalue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = destportId;
        result = 31 * result + (starttime != null ? starttime.hashCode() : 0);
        result = 31 * result + (endtime != null ? endtime.hashCode() : 0);
        result = 31 * result + (statictisvalue != null ? statictisvalue.hashCode() : 0);
        temp = Double.doubleToLongBits(average);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(variance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    public String toString(){
        return "DestPort = {" +
                "id=" + destportId +
                ",starttime=" + starttime +
                ",endtime=" + endtime +
                ",statictisvalue=" + statictisvalue +
                ",average=" + average +
                ",variance=" + variance +
                "}";
    }
}
