package com.bjpowernode.vo;

public class StudentAndClassroomVo {

    private String sid;
    private int sage;
    private String sname;
    private String saddress;

    private String cid;
    private String cname;


    @Override
    public String toString() {
        return "StudentAndClassroomVo{" +
                "sid='" + sid + '\'' +
                ", sage=" + sage +
                ", sname='" + sname + '\'' +
                ", saddress='" + saddress + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

}
