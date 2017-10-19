package com.example.administrator.hellocommander;

//전역자 테이블에 대한 아티클

public class DeadArticle {

    private String img2;
    private String Name2;
    private String JR2;
    private String GJ2;
    private String OD2;

    public DeadArticle(String img2, String Name2, String JR2, String GJ2, String OD2){
        this.img2 = img2;
        this.Name2 = Name2;
        this.JR2 = JR2;
        this.GJ2 = GJ2;
        this.OD2 = OD2;
    }

    public String getImg2() {
        return img2;
    }

    public String getName2() {
        return Name2;
    }

    public String getJR2() {
        return JR2;
    }

    public String getGJ2() {
        return GJ2;
    }

    public String getOD2() {
        return OD2;
    }
}
