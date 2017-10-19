package com.example.administrator.hellocommander;

//병사 테이블에 대한 아티클

public class MainArticle {
    private String img;
    private String GG;
    private String Name;

    private String JR;
    private String GJ;
    private String BD;

    private String ID;
    private String OD;
    private String NOW;

    public MainArticle(String img, String Name, String GG, String JR, String GJ, String BD, String ID, String OD,String NOW)
    {
        this.img =img;
        this.GG = GG;
        this.Name = Name;

        this.JR = JR;
        this.GJ = GJ;
        this.BD = BD;

        this.ID = ID;
        this.OD = OD;
        this.NOW = NOW;
    }

    public String getImg() {
        return img;
    }

    public String getGG() {
        return GG;
    }

    public String getName() {
        return Name;
    }


    public String getJR() {
        return JR;
    }

    public String getGJ() {
        return GJ;
    }

    public String getBD() {
        return BD;
    }


    public String getID() {
        return ID;
    }

    public String getOD() {
        return OD;
    }

    public String getNOW() {
        return NOW;
    }
}
