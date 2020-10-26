package com.buckun.siparisuygulamasi.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.buckun.siparisuygulamasi.R;
import com.buckun.siparisuygulamasi.model.Products;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private LayoutInflater inf;
    private List<Products> urls;
    private Activity ac;

    public ProductAdapter(Activity activity, List<Products> urls) {
        inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.urls = urls;
        ac = activity;
    }

    @Override
    public int getCount() {//ürün sayısı kadar dön
        return urls.size();
    }

    @Override
    public Products getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        rowView = inf.inflate(R.layout.productrow, null);
        TextView productTitle = rowView.findViewById(R.id.txtUrunBaslik);
        ImageView image = rowView.findViewById(R.id.resim);

        Products ul = urls.get(position);
        productTitle.setText(ul.getProductName());
        Picasso.get().load(ul.getImages()).into(image);

        return rowView;
    }
}
