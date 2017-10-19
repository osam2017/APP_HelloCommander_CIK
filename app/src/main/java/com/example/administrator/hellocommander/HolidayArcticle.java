package com.example.administrator.hellocommander;

//휴가일정 테이블에 대한 아티클

public class HolidayArcticle {
    private String Name4;
    private String ARf2;
    private String ARs2;
    private String Content2;
    private String fnum2;
    private String snum2;

    public HolidayArcticle(String Name3, String ARf, String ARs, String Content, String fnum, String snum){
        this.Name4 = Name3;
        this.ARf2 = ARf;
        this.ARs2 = ARs;
        this.Content2 = Content;
        this.fnum2 = fnum;
        this.snum2 = snum;
    }

    public String getName4() {
        return Name4;
    }

    public String getARf2() {
        return ARf2;
    }

    public String getARs2() {
        return ARs2;
    }

    public String getContent2() {
        return Content2;
    }

    public String getFnum2() {
        return fnum2;
    }

    public String getSnum2() {
        return snum2;
    }
}
