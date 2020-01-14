package com.enesceltik.ogrencivericekme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestInterface {

    @GET("users/getall")
    Call<List<repo>> GetAll();

    @POST("users/create")
    Call<Void> YeniKayit (@Body Ogrenci Ogr);

    @PUT ("users/update/{id}")
    Call<Void> Guncelle (@Path("id") String id,@Body Ogrenci Ogr );

    @DELETE("users/delete/{id}")
    Call<Void> Sil (@Path("id") String id);

    @POST("users/GetUserByOgrNo")
    Call<OgrenciTum> GetirOgrenci (@Body OgrenciSorgu OgrSorgu);}

