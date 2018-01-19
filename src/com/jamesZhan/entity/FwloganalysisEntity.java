package com.jamesZhan.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 16255 on 2017/11/2.
 */
public class FwloganalysisEntity {
    @Id
    private Long id;
    @Column
    private String internalIp;
    @Column
    private Date timestamp;
    @Column
    private String originalSrcIp;
    @Column
    private String originalSrcPort;
    @Column
    private String originalDestIp;
    @Column
    private String originalDestPort;
    @Column
    private Integer protocolNumber;
    @Column
    private String safetyDomain;
    @Column
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

        FwloganalysisEntity that = (FwloganalysisEntity) o;

        if (id != that.id) return false;
        if (internalIp != null ? !internalIp.equals(that.internalIp) : that.internalIp != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        if (originalSrcIp != null ? !originalSrcIp.equals(that.originalSrcIp) : that.originalSrcIp != null)
            return false;
        if (originalSrcPort != null ? !originalSrcPort.equals(that.originalSrcPort) : that.originalSrcPort != null)
            return false;
        if (originalDestIp != null ? !originalDestIp.equals(that.originalDestIp) : that.originalDestIp != null)
            return false;
        if (originalDestPort != null ? !originalDestPort.equals(that.originalDestPort) : that.originalDestPort != null)
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
        result = 31 * result + (originalSrcIp != null ? originalSrcIp.hashCode() : 0);
        result = 31 * result + (originalSrcPort != null ? originalSrcPort.hashCode() : 0);
        result = 31 * result + (originalDestIp != null ? originalDestIp.hashCode() : 0);
        result = 31 * result + (originalDestPort != null ? originalDestPort.hashCode() : 0);
        result = 31 * result + (protocolNumber != null ? protocolNumber.hashCode() : 0);
        result = 31 * result + (safetyDomain != null ? safetyDomain.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Fwlog{" +
                "id=" + id +
                ", internalIp='" + internalIp + '\'' +
                ", timestamp=" + timestamp +
                ", originalSrcIp='" + originalSrcIp + '\'' +
                ", originalSrcPort='" + originalSrcPort + '\'' +
                ", originalDestIp='" + originalDestIp + '\'' +
                ", originalDestPort='" + originalDestPort + '\'' +
                ", protocolNumber=" + protocolNumber +
                ", safetyDomain='" + safetyDomain + '\'' +
                ", action=" + action +
                '}';
    }
}
