package com.enesceltik.ogrencivericekme;
public class OgrenciTum {
    private String id;
    private String ogrNo;
    private String  ogrAd;
    private String  ogrSoyad;
    private String  ogrGNO;

    public OgrenciTum(String id, String ogrNo, String ogrAd, String ogrSoyad, String ogrGNO) {
        this.id = id;
        this.ogrNo = ogrNo;
        this.ogrAd = ogrAd;
        this.ogrSoyad = ogrSoyad;
        this.ogrGNO = ogrGNO;
    }
    public String getId(){
        return id;
    }
    public String getOgrAd(){
        return ogrAd;
    }

    public void setOgrAd(String ogrAd) {
        this.ogrAd = ogrAd;
    }
    public String getOgrSoyad(){
        return ogrSoyad;
    }

    public void setOgrSoyad(String ogrSoyad) {
        this.ogrSoyad = ogrSoyad;
    }
    public String getOgrNo(){
        return ogrNo;
    }

    public void setOgrNo(String ogrNo) {
        this.ogrNo = ogrNo;
    }
    public String getOgrGNO(){
        return ogrGNO;
    }
    public void setOgrGNO(String ogrGNO) {
        this.ogrGNO = ogrGNO;
    }

    public void setId(String id) {
        this.id = id;
    }
}