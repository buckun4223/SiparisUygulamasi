package com.buckun.siparisuygulamasi.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.buckun.siparisuygulamasi.R;
import com.buckun.siparisuygulamasi.model.Kategori;

import java.util.List;

public class CatagoryAdapter extends BaseAdapter {

    private LayoutInflater inf;
    private List<Kategori> katls;
    private Activity ac;


    public CatagoryAdapter(Activity activity, List<Kategori> urls) {
        inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.katls = urls;
        ac = activity;
    }


    @Override
    public int getCount() {//ürün sayısı kadar dön
        return katls.size();
    }

    @Override
    public Kategori getItem(int position) {
        return katls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        rowView = inf.inflate(R.layout.katrow, null);
        TextView catName = rowView.findViewById(R.id.txtCatName);

        Kategori ul = katls.get(position);
        catName.setText(ul.getCatogryName());

        return rowView;
    }

}
