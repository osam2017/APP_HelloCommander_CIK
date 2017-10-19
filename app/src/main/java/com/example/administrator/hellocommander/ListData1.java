package com.example.administrator.hellocommander;

//리스트 데이터1을 사용하기 위한 아티클

public class ListData1 {
    private String imgName;
    private String textgyugup;
    private String textName;
    private String textjongryu;
    private String textnow;

    ListData1(String i1, String t1, String t2, String t3, String t4){
        this.imgName = i1;
        this.textgyugup = t1;
        this.textjongryu = t2;
        this.textName = t3;
        this.textnow = t4;
    }

    public String getImgName() {
        return imgName;
    }

    public String getTextgyugup() {
        return textgyugup;
    }

    public String getTextjongryu() {
        return textjongryu;
    }

    public String getTextName() {
        return textName;
    }

    public String getTextnow() {
        return textnow;
    }
}
