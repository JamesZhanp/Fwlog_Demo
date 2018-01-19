package com.jamesZhan.entity;

/**
 * Created by 16255 on 2017/11/5.
 */
public class SensitiveportEntity {
    private int id;
    private int port;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensitiveportEntity that = (SensitiveportEntity) o;

        if (id != that.id) return false;
        if (port != that.port) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + port;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }
}
