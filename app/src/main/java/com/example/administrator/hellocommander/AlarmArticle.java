package com.example.administrator.hellocommander;

//알람 테이블에 대한 아티클

public class AlarmArticle {
    private String Name3;
    private String ARf;
    private String ARs;
    private String Content;
    private String fnum;
    private String snum;

    public AlarmArticle(String Name3, String ARf, String ARs, String Content, String fnum, String snum){
        this.Name3 = Name3;
        this.ARf = ARf;
        this.ARs = ARs;
        this.Content = Content;
        this.fnum = fnum;
        this.snum = snum;
    }

    public String getName3() {
        return Name3;
    }

    public String getARf() {
        return ARf;
    }

    public String getARs() {
        return ARs;
    }

    public String getContent() {
        return Content;
    }

    public String getFnum() {
        return fnum;
    }

    public String getSnum() {
        return snum;
    }
}
