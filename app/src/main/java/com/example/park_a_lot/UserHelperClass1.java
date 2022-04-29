package com.example.park_a_lot;

public class UserHelperClass1 {
    String Bcost,Bdate,Btime ,Bvid ,Bduration,Buid;

    public UserHelperClass1() {

    }

    public UserHelperClass1(String bcost, String bdate, String btime, String bvid, String bduration, String buid) {
        Bcost = bcost;
        Bdate = bdate;
        Btime = btime;
        Bvid = bvid;
        Bduration = bduration;
        Buid = buid;
    }

    public String getBcost() {
        return Bcost;
    }

    public void setBcost(String bcost) {
        Bcost = bcost;
    }

    public String getBdate() {
        return Bdate;
    }

    public void setBdate(String bdate) {
        Bdate = bdate;
    }

    public String getBtime() {
        return Btime;
    }

    public void setBtime(String btime) {
        Btime = btime;
    }

    public String getBvid() {
        return Bvid;
    }

    public void setBvid(String bvid) {
        Bvid = bvid;
    }

    public String getBduration() {
        return Bduration;
    }

    public void setBduration(String bduration) {
        Bduration = bduration;
    }

    public String getBuid() {
        return Buid;
    }

    public void setBuid(String buid) {
        Buid = buid;
    }
}
