package com.example.fruitshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("goods")
public class goods {
    private String Gno;
    private String Gname;
    private String Gdescription;
    private String Gphoto;
    private float Gprice;
    private String Gstate;

    public goods() {
    }

    public goods(String gno, String gname, String gdescription, String gphoto, float gprice, String gstate) {
        Gno = gno;
        Gname = gname;
        Gdescription = gdescription;
        Gphoto = gphoto;
        Gprice = gprice;
        Gstate = gstate;
    }

    public String getGno() {
        return Gno;
    }

    public void setGno(String gno) {
        Gno = gno;
    }

    public String getGname() {
        return Gname;
    }

    public void setGname(String gname) {
        Gname = gname;
    }

    public String getGdescription() {
        return Gdescription;
    }

    public void setGdescription(String gdescription) {
        Gdescription = gdescription;
    }

    public String getGphoto() {
        return Gphoto;
    }

    public void setGphoto(String gphoto) {
        Gphoto = gphoto;
    }

    public float getGprice() {
        return Gprice;
    }

    public void setGprice(float gprice) {
        Gprice = gprice;
    }

    public String getGstate() {
        return Gstate;
    }

    public void setGstate(String gstate) {
        Gstate = gstate;
    }

    @Override
    public String toString() {
        return "goods{" +
                "Gno='" + Gno + '\'' +
                ", Gname='" + Gname + '\'' +
                ", Gdescription='" + Gdescription + '\'' +
                ", Gphoto='" + Gphoto + '\'' +
                ", Gprice=" + Gprice +
                ", Gstate='" + Gstate + '\'' +
                '}';
    }
}
