package com.example.fruitshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("shop")
public class shop {
    private String Sname;
    private String Saccount;
    private String Spassword;

    public shop() {
    }

    public shop(String sname, String saccount, String spassword) {
        Sname = sname;
        Saccount = saccount;
        Spassword = spassword;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSaccount() {
        return Saccount;
    }

    public void setSaccount(String saccount) {
        Saccount = saccount;
    }

    public String getSpassword() {
        return Spassword;
    }

    public void setSpassword(String spassword) {
        Spassword = spassword;
    }

    @Override
    public String toString() {
        return "shop{" +
                "Sname='" + Sname + '\'' +
                ", Saccount='" + Saccount + '\'' +
                ", Spassword='" + Spassword + '\'' +
                '}';
    }
}
