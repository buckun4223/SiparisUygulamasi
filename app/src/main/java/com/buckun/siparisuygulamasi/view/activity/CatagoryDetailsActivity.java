package com.buckun.siparisuygulamasi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.buckun.siparisuygulamasi.R;
import com.buckun.siparisuygulamasi.model.Kategori;
import com.buckun.siparisuygulamasi.model.catagorypojo.Kategoriler;
import com.buckun.siparisuygulamasi.model.core.catagory.CatagoryContract;
import com.buckun.siparisuygulamasi.presenter.catagorypresenter.CatagoryPresenter;
import com.buckun.siparisuygulamasi.view.adapter.CatagoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class CatagoryDetailsActivity extends AppCompatActivity implements CatagoryContract.View {

    String selectCatagory;
    CatagoryPresenter catagoryPresenter;
    List<Kategori> category;
    ListView liste;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory_details);
        liste = findViewById(R.id.cat_detail_list);
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        selectCatagory = getIntent().getStringExtra("selectCatagory");
        category = new ArrayList<>();
        catagoryPresenter = new CatagoryPresenter(this);
        catagoryPresenter.fetchCatagory();
    }
    @Override
    public void onFetchSuccess(List<Kategoriler> catagoryList) {
        progressDialog.dismiss();
        for (int i = 0; i < catagoryList.get(0).getCategories().size(); i++) {
            String topCatogryId = catagoryList.get(0).getCategories().get(i).getTopCatogryId();
            if (topCatogryId.equals(selectCatagory)) {
                String catId = catagoryList.get(0).getCategories().get(i).getCatogryId();
                String catName = catagoryList.get(0).getCategories().get(i).getCatogryName();
                Kategori kategori = new Kategori(catId, catName);
                category.add(kategori);
            }
        }

        CatagoryAdapter adapter = new CatagoryAdapter(this, category);
        liste.setAdapter(adapter);

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    //Bir sonraki sayfaya geçildi.
                    Intent i = new Intent(CatagoryDetailsActivity.this, ProductActivity.class);
                    i.putExtra("CatagoryId",category.get(position).getCatogryId());
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ID yakalama hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onFetchFail() {
        progressDialog.dismiss();

    }

    @Override
    public void onProcessStart() {

    }

    @Override
    public void onProcessStop() {

    }
}