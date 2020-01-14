package com.enesceltik.ogrencivericekme;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RestInterface restInterface;

    int Pozisyon=-1;

    List<OgrenciTum> ogrenciListesi=new ArrayList<OgrenciTum>();
    Button btn_ekle, btn_sil, btn_guncelle;
    OgrenciTum tmp_OgrenciTum;
    OzelAdapter ozelAdapter;
    ListView lstView_Ogrenciler;
    EditText editText_OgrAD, editText_OgrSoyAD, editText_OgrNo, editText_OgrGNO;
    TextView textView_OgrID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_OgrID=findViewById(R.id.txtview_OgrID);
        editText_OgrAD=findViewById(R.id.edtText_OgrAD);
        editText_OgrSoyAD=findViewById(R.id.edtTxt_OgrSoyAd);
        editText_OgrNo=findViewById(R.id.edtTxt_OgrNo);
        editText_OgrGNO=findViewById(R.id.edtTxt_GNO);

        btn_ekle=findViewById(R.id.btn_Ekle);
        btn_guncelle=findViewById(R.id.btn_Guncelle);
        btn_sil=findViewById(R.id.btn_Sil);
        lstView_Ogrenciler=findViewById(R.id.lstView_Ogrenciler);

        restInterface = ApiClient.getClient().create(RestInterface.class);

        textView_OgrID.setText("Id: ");

        final ArrayList<OgrenciTum> ogrenciler = new ArrayList<>();

        Call<List<repo>> call = restInterface.GetAll();
        call.enqueue(new Callback<List<repo>>() {
            @Override
            public void onResponse(Call<List<repo>> call, Response<List<repo>> response) {
                if (response.code() == 200)
                {
                   // Başarılı
                    List<repo> reposList = response.body();

                    for (int i = 0 ; i < reposList.size();i++)
                    {
                        tmp_OgrenciTum= new OgrenciTum(String.valueOf(reposList.get(i).id),reposList.get(i).ogrNo,reposList.get(i).ogrAd,reposList.get(i).ogrSoyad,String.valueOf(reposList.get(i).ogrGNO));// tmp_OgrenciTum.setId(String.valueOf(reposList.get(i).id));
                        ogrenciListesi.add(tmp_OgrenciTum);
                    }
                    Ogrencileri_Listeye_Atma();
                }
                else {
                    Toast.makeText(MainActivity.this, "Başarısız", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<repo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Başarısız", Toast.LENGTH_SHORT).show();

            }
        });
        lstView_Ogrenciler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            tmp_OgrenciTum=ozelAdapter.getItem(position);
            Pozisyon=position;
            textView_OgrID.setText("Id: "+tmp_OgrenciTum.getId());
            editText_OgrAD.setText(tmp_OgrenciTum.getOgrAd());
            editText_OgrSoyAD.setText(tmp_OgrenciTum.getOgrSoyad());
            editText_OgrNo.setText(tmp_OgrenciTum.getOgrNo());
            editText_OgrGNO.setText(tmp_OgrenciTum.getOgrGNO());
            }
        });
    }
    public void Ogrencileri_Listeye_Atma(){
        ozelAdapter=new OzelAdapter(MainActivity.this, ogrenciListesi);
        lstView_Ogrenciler.setAdapter(ozelAdapter);

    }


    public void Ekle_yeniogr(View view) {
        String no="-1", ad="-1", soyad="-1", gno="-1";
        if(editText_OgrGNO.getText().toString().matches("") || editText_OgrGNO.getText().toString().matches("Gno"))
        {
            Toast.makeText(this, "Öğrenci GNO giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            gno=editText_OgrGNO.getText().toString();
        }
        if(editText_OgrSoyAD.getText().toString().matches("") || editText_OgrSoyAD.getText().toString().matches("Soyad"))
        {
            Toast.makeText(this, "Öğrenci SOYAD giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            soyad=editText_OgrSoyAD.getText().toString();
        }
        if(editText_OgrNo.getText().toString().matches("") || editText_OgrNo.getText().toString().matches("No"))
        {
            Toast.makeText(this, "Öğrenci NO giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            no=editText_OgrNo.getText().toString();
        }
        if(editText_OgrAD.getText().toString().matches("") || editText_OgrAD.getText().toString().matches("Ad"))
        {
            Toast.makeText(this, "Öğrenci ADI giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ad=editText_OgrAD.getText().toString();
        }
        Ogrenci yeniOgrenci = new Ogrenci(no,ad,soyad,gno);
        Call <Void> call ;
        call = restInterface.YeniKayit(yeniOgrenci);
        final String finalNo = no;
        final String finalAd = ad;
        final String finalSoyad = soyad;
        final String finalGno = gno;
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200)
                {
                    Toast.makeText(MainActivity.this, "Kayit Başarili", Toast.LENGTH_SHORT).show();
                    ogrenciListesi.add(new OgrenciTum( Integer.valueOf(Integer.valueOf(ogrenciListesi.get(ogrenciListesi.size() - 1).getId())+1).toString(), finalNo, finalAd, finalSoyad, finalGno));
                    ozelAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "HATA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "HATA", Toast.LENGTH_SHORT).show();

            }
        });
        ozelAdapter.notifyDataSetChanged();
    }

    public void Guncelle(View view) {
        String no="-1", ad="-1", soyad="-1", gno="-1";
        if(editText_OgrGNO.getText().toString().matches("") || editText_OgrGNO.getText().toString().matches("Gno"))
        {
            Toast.makeText(this, "Öğrenci GNO giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            gno=editText_OgrGNO.getText().toString();
        }
        if(editText_OgrSoyAD.getText().toString().matches("") || editText_OgrSoyAD.getText().toString().matches("Soyad"))
        {
            Toast.makeText(this, "Öğrenci SOYAD giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            soyad=editText_OgrSoyAD.getText().toString();
        }
        if(editText_OgrNo.getText().toString().matches("") || editText_OgrNo.getText().toString().matches("No"))
        {
            Toast.makeText(this, "Öğrenci NO giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            no=editText_OgrNo.getText().toString();
        }
        if(editText_OgrAD.getText().toString().matches("") || editText_OgrAD.getText().toString().matches("Ad"))
        {
            Toast.makeText(this, "Öğrenci ADI giriniz", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ad=editText_OgrAD.getText().toString();
        }
        Ogrenci GuncellenecekOgrenci = new Ogrenci(no,ad,soyad,gno);

        Call <Void> call;
        call = restInterface.Guncelle(ogrenciListesi.get(Pozisyon).getId(),GuncellenecekOgrenci);
        final String finalNo = no;
        final String finalAd = ad;
        final String finalSoyad = soyad;
        final String finalGno = gno;
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200)
                {
                    Toast.makeText(MainActivity.this, "Güncelleme Başarili", Toast.LENGTH_SHORT).show();
                    ogrenciListesi.set(Pozisyon, new OgrenciTum(ogrenciListesi.get(Pozisyon).getId(), finalNo, finalAd, finalSoyad, finalGno));
                    ozelAdapter.notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "HATA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void Sil(View view)
    {

        for(int i=0;i<ogrenciListesi.size();i++)
        {
            if(textView_OgrID.getText().equals("Id: "+ogrenciListesi.get(i).getId()))
            {
                Call <Void> call;
                call = restInterface.Sil(ogrenciListesi.get(Pozisyon).getId());
                call.enqueue(new Callback<Void>()
                {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response)
                    {
                        if (response.code() == 200)
                        {
                            Toast.makeText(MainActivity.this, "Silme Başarili", Toast.LENGTH_SHORT).show();
                            ogrenciListesi.remove(Pozisyon);
                            ozelAdapter.notifyDataSetChanged();
                            Pozisyon=-1;
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "HATA", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t)
                    {
                    Toast.makeText(MainActivity.this, "HATA", Toast.LENGTH_SHORT).show();
                    }
                 });
            }
        }
        if(Pozisyon==-1 || textView_OgrID.getText().equals("Id: "))
        {
            Toast.makeText(this, "Silmek İstediğiniz Öğrenci Bulunamamıştır", Toast.LENGTH_SHORT).show();
        }
    }



}