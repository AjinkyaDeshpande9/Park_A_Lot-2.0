package com.example.park_a_lot;

public class UserHelperClass {
    String uname,uemail,upassword,uphone,uvecnumber,uvectype;

    public UserHelperClass(){

    }

    public UserHelperClass(String uname, String uemail, String upassword, String uphone, String uvecnumber, String uvectype) {
        this.uname = uname;
        this.uemail = uemail;
        this.upassword = upassword;
        this.uphone = uphone;
        this.uvecnumber = uvecnumber;
        this.uvectype = uvectype;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUvecnumber() {
        return uvecnumber;
    }

    public void setUvecnumber(String uvecnumber) {
        this.uvecnumber = uvecnumber;
    }

    public String getUvectype() {
        return uvectype;
    }

    public void setUvectype(String uregvectype) {
        this.uvectype = uregvectype;
    }
}
