package com.example.fruitshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.text.SimpleDateFormat;

@TableName("orders")
public class order {
    private String Ono;
    private String Otime;
    private String Ostate;
    private String Uname;
    private String Utele;
    private String Uadd;
    private String Gname;

    public order() {
    }

    public order(String ono, String otime, String ostate, String uname, String utele, String uadd, String gname) {
        Ono = ono;
        Otime = otime;
        Ostate = ostate;
        Uname = uname;
        Utele = utele;
        Uadd = uadd;
        Gname = gname;
    }

    public String getOno() {
        return Ono;
    }

    public void setOno(String ono) {
        Ono = ono;
    }

    public String getOtime() {
        return Otime;
    }

    public void setOtime(String otime) {
        Otime = otime;
    }

    public String getOstate() {
        return Ostate;
    }

    public void setOstate(String ostate) {
        Ostate = ostate;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getUtele() {
        return Utele;
    }

    public void setUtele(String utele) {
        Utele = utele;
    }

    public String getUadd() {
        return Uadd;
    }

    public void setUadd(String uadd) {
        Uadd = uadd;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return "order{" +
                "Ono='" + Ono + '\'' +
                ", Otime='" + Otime + '\'' +
                ", Ostate='" + Ostate + '\'' +
                ", Uname='" + Uname + '\'' +
                ", Utele='" + Utele + '\'' +
                ", Uadd='" + Uadd + '\'' +
                ", Gname='" + Gname + '\'' +
                '}';
    }
}
