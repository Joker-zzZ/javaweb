package com.bjpowernode.entity;

public class Province {
    private Integer id;
    private String name;
    private String shenghui;
    private String jiancheng;

    public Province(Integer id, String name, String shenghui, String jiancheng) {
        this.id = id;
        this.name = name;
        this.shenghui = shenghui;
        this.jiancheng = jiancheng;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shenghui='" + shenghui + '\'' +
                ", jiancheng='" + jiancheng + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShenghui() {
        return shenghui;
    }

    public void setShenghui(String shenghui) {
        this.shenghui = shenghui;
    }

    public String getJiancheng() {
        return jiancheng;
    }

    public void setJiancheng(String jiancheng) {
        this.jiancheng = jiancheng;
    }
}
