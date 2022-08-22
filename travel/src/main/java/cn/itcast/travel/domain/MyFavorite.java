package cn.itcast.travel.domain;

import java.io.Serializable;

public class MyFavorite implements Serializable {

    private int rid;
    private String date;
    private int uid;

    public MyFavorite() {
    }

    public MyFavorite(int rid, String date, int uid) {
        this.rid = rid;
        this.date = date;
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
