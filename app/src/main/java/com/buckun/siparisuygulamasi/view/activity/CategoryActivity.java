package com.buckun.siparisuygulamasi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.buckun.siparisuygulamasi.R;
import com.buckun.siparisuygulamasi.model.Kategori;
import com.buckun.siparisuygulamasi.model.catagorypojo.Kategoriler;
import com.buckun.siparisuygulamasi.model.core.catagory.CatagoryContract;
import com.buckun.siparisuygulamasi.presenter.catagorypresenter.CatagoryPresenter;
import com.buckun.siparisuygulamasi.view.adapter.CatagoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements CatagoryContract.View {

    CatagoryPresenter catagoryPresenter;
    List<Kategori> category;
    ListView liste;
    TextView userName;
    ImageView userImage;
    Boolean currentActivity = false;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        liste = findViewById(R.id.catList);
        userName = findViewById(R.id.userAccount);
        userImage = findViewById(R.id.userImage);
        progressDialog = new ProgressDialog(this);
        userName.setText(getIntent().getStringExtra("email"));
        progressDialog.show();
        catagoryPresenter = new CatagoryPresenter(this);
        catagoryPresenter.fetchCatagory();

        currentActivity = true;

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent navigation = new Intent(CategoryActivity.this, PermissionsActivity.class);
                startActivity(navigation);
            }
        });

        FloatingActionButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent navigation = new Intent(CategoryActivity.this, LoginActivity.class);
                startActivity(navigation);
                finish();
            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public void onFetchSuccess(final List<Kategoriler> catagoryList) {
        progressDialog.dismiss();
            category = new ArrayList<>();
            for (int i = 0; i < catagoryList.get(0).getCategories().size(); i++) {
                String topCatogryId = catagoryList.get(0).getCategories().get(i).getTopCatogryId();
                if (topCatogryId.equals("0")) {
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
                        //Tıklanan'a ait olan bilgileri daha önce set edilmiş sınıftan get edilerek alındı
                        Kategori ur = category.get(position);
                        Intent i = new Intent(CategoryActivity.this, CatagoryDetailsActivity.class);
                        i.putExtra("selectCatagory", ur.getCatogryId());
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
        Toast.makeText(this, "Catagory: fail ", Toast.LENGTH_LONG).show();


    }

    @Override
    public void onProcessStart() {

    }

    @Override
    public void onProcessStop() {

    }
}