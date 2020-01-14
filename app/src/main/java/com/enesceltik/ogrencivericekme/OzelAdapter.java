package com.enesceltik.ogrencivericekme;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class OzelAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<OgrenciTum> mKisiListesi;

    public OzelAdapter(Activity activity,List<OgrenciTum> ogrenciler)
    {
        mInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mKisiListesi=ogrenciler;
    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public OgrenciTum getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        TextView txt_Id, txt_Ad, txt_SoyAD, txt_No, txt_GNO;


        satirView=mInflater.inflate(R.layout.satir_layout, null);
        txt_Ad=satirView.findViewById(R.id.txt_OgrAD);
        txt_Id=satirView.findViewById(R.id.txt_Id);
        txt_SoyAD=satirView.findViewById(R.id.txt_OgrSoyAd);
        txt_No=satirView.findViewById(R.id.txt_OgrNO);
        txt_GNO=satirView.findViewById(R.id.txt_OgrGNO);

        OgrenciTum tmpOgrenci=mKisiListesi.get(position);
        txt_Id.setText(tmpOgrenci.getId());
        txt_Ad.setText(tmpOgrenci.getOgrAd());
        txt_SoyAD.setText(tmpOgrenci.getOgrSoyad());
        txt_No.setText(tmpOgrenci.getOgrNo());
        txt_GNO.setText(tmpOgrenci.getOgrGNO());

        return satirView;
    }
}