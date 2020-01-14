package com.enesceltik.ogrencivericekme;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private  static Retrofit retrofit = null;
    private static String Base_Url ="http://bigdata.eskapi.me/";
    public static Retrofit getClient(){
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
        else
        {
            return retrofit;
        }

    }

}