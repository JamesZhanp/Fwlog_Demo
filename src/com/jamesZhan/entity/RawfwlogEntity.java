package com.jamesZhan.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 16255 on 2017/11/2.
 */
public class RawfwlogEntity {
    @Id
    private Long id;
    @Column
    private String internalIp;
    private Date timestamp;
    private Date anotherTimestamp;
    private String mathedStrategy;
    private String originalSrcIp;
    private String originalSrcPort;
    private String originalDestIp;
    private String originalDestPort;
    private String convertedSrcIp;
    private String convertedSrcPort;
    private String convertedDestIp;
    private String convertedDestPort;
    private Integer protocolNumber;
    private String safetyDomain;
    private Integer action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInternalIp() {
        return internalIp;
    }

    public void setInternalIp(String internalIp) {
        this.internalIp = internalIp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getAnotherTimestamp() {
        return anotherTimestamp;
    }

    public void setAnotherTimestamp(Date anotherTimestamp) {
        this.anotherTimestamp = anotherTimestamp;
    }

    public String getMathedStrategy() {
        return mathedStrategy;
    }

    public void setMathedStrategy(String mathedStrategy) {
        this.mathedStrategy = mathedStrategy;
    }

    public String getOriginalSrcIp() {
        return originalSrcIp;
    }

    public void setOriginalSrcIp(String originalSrcIp) {
        this.originalSrcIp = originalSrcIp;
    }

    public String getOriginalSrcPort() {
        return originalSrcPort;
    }

    public void setOriginalSrcPort(String originalSrcPort) {
        this.originalSrcPort = originalSrcPort;
    }

    public String getOriginalDestIp() {
        return originalDestIp;
    }

    public void setOriginalDestIp(String originalDestIp) {
        this.originalDestIp = originalDestIp;
    }

    public String getOriginalDestPort() {
        return originalDestPort;
    }

    public void setOriginalDestPort(String originalDestPort) {
        this.originalDestPort = originalDestPort;
    }

    public String getConvertedSrcIp() {
        return convertedSrcIp;
    }

    public void setConvertedSrcIp(String convertedSrcIp) {
        this.convertedSrcIp = convertedSrcIp;
    }

    public String getConvertedSrcPort() {
        return convertedSrcPort;
    }

    public void setConvertedSrcPort(String convertedSrcPort) {
        this.convertedSrcPort = convertedSrcPort;
    }

    public String getConvertedDestIp() {
        return convertedDestIp;
    }

    public void setConvertedDestIp(String convertedDestIp) {
        this.convertedDestIp = convertedDestIp;
    }

    public String getConvertedDestPort() {
        return convertedDestPort;
    }

    public void setConvertedDestPort(String convertedDestPort) {
        this.convertedDestPort = convertedDestPort;
    }

    public Integer getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(Integer protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSafetyDomain() {
        return safetyDomain;
    }

    public void setSafetyDomain(String safetyDomain) {
        this.safetyDomain = safetyDomain;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawfwlogEntity that = (RawfwlogEntity) o;

        if (id != that.id) return false;
        if (internalIp != null ? !internalIp.equals(that.internalIp) : that.internalIp != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (anotherTimestamp != null ? !anotherTimestamp.equals(that.anotherTimestamp) : that.anotherTimestamp != null)
            return false;
        if (mathedStrategy != null ? !mathedStrategy.equals(that.mathedStrategy) : that.mathedStrategy != null)
            return false;
        if (originalSrcIp != null ? !originalSrcIp.equals(that.originalSrcIp) : that.originalSrcIp != null)
            return false;
        if (originalSrcPort != null ? !originalSrcPort.equals(that.originalSrcPort) : that.originalSrcPort != null)
            return false;
        if (originalDestIp != null ? !originalDestIp.equals(that.originalDestIp) : that.originalDestIp != null)
            return false;
        if (originalDestPort != null ? !originalDestPort.equals(that.originalDestPort) : that.originalDestPort != null)
            return false;
        if (convertedSrcIp != null ? !convertedSrcIp.equals(that.convertedSrcIp) : that.convertedSrcIp != null)
            return false;
        if (convertedSrcPort != null ? !convertedSrcPort.equals(that.convertedSrcPort) : that.convertedSrcPort != null)
            return false;
        if (convertedDestIp != null ? !convertedDestIp.equals(that.convertedDestIp) : that.convertedDestIp != null)
            return false;
        if (convertedDestPort != null ? !convertedDestPort.equals(that.convertedDestPort) : that.convertedDestPort != null)
            return false;
        if (protocolNumber != null ? !protocolNumber.equals(that.protocolNumber) : that.protocolNumber != null)
            return false;
        if (safetyDomain != null ? !safetyDomain.equals(that.safetyDomain) : that.safetyDomain != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (internalIp != null ? internalIp.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (anotherTimestamp != null ? anotherTimestamp.hashCode() : 0);
        result = 31 * result + (mathedStrategy != null ? mathedStrategy.hashCode() : 0);
        result = 31 * result + (originalSrcIp != null ? originalSrcIp.hashCode() : 0);
        result = 31 * result + (originalSrcPort != null ? originalSrcPort.hashCode() : 0);
        result = 31 * result + (originalDestIp != null ? originalDestIp.hashCode() : 0);
        result = 31 * result + (originalDestPort != null ? originalDestPort.hashCode() : 0);
        result = 31 * result + (convertedSrcIp != null ? convertedSrcIp.hashCode() : 0);
        result = 31 * result + (convertedSrcPort != null ? convertedSrcPort.hashCode() : 0);
        result = 31 * result + (convertedDestIp != null ? convertedDestIp.hashCode() : 0);
        result = 31 * result + (convertedDestPort != null ? convertedDestPort.hashCode() : 0);
        result = 31 * result + (protocolNumber != null ? protocolNumber.hashCode() : 0);
        result = 31 * result + (safetyDomain != null ? safetyDomain.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "rawFwlog{" +
                "id=" + id +
                ", internalIp='" + internalIp + '\'' +
                ", timestamp=" + timestamp +
                ", anotherTimestamp=" + anotherTimestamp +
                ", mathedStrategy='" + mathedStrategy + '\'' +
                ", orignalSrcIp='" + originalSrcIp + '\'' +
                ", orignalSrcPort='" + originalSrcPort + '\'' +
                ", orignalDestIp='" + originalDestIp + '\'' +
                ", orignalDestPort='" + originalDestPort + '\'' +
                ", convertedSrcIp='" + convertedSrcIp + '\'' +
                ", convertedSrcPort='" + convertedSrcPort + '\'' +
                ", convertedDestIp='" + convertedDestIp + '\'' +
                ", convertedDestPort='" + convertedDestPort + '\'' +
                ", protocolNumber=" + protocolNumber +
                ", safetyDomain='" + safetyDomain + '\'' +
                ", action=" + action +
                "}";
    }
}
