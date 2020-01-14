package com.enesceltik.ogrencivericekme;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class repo {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("ogrNo")
    @Expose
    public String ogrNo;
    @SerializedName("ogrAd")
    @Expose
    public String ogrAd;
    @SerializedName("ogrSoyad")
    @Expose
    public String ogrSoyad;
    @SerializedName("ogrGNO")
    @Expose
    public Float ogrGNO;
}
