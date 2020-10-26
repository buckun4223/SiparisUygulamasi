package com.buckun.siparisuygulamasi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.buckun.siparisuygulamasi.R;
import com.buckun.siparisuygulamasi.model.Products;
import com.buckun.siparisuygulamasi.model.core.product.ProductContract;
import com.buckun.siparisuygulamasi.model.productsPojo.Bilgiler;
import com.buckun.siparisuygulamasi.model.productsPojo.Category;
import com.buckun.siparisuygulamasi.model.productsPojo.Image;
import com.buckun.siparisuygulamasi.presenter.productpresenter.ProductPresenter;
import com.buckun.siparisuygulamasi.view.adapter.ProductAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity implements ProductContract.View {

    ProductPresenter productPresenter;
    String catagoryId;
    ListView ls;
    List<Products> listeArray;
    List<Image> images;
    TextView urunYok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ls = findViewById(R.id.urunlerList);
        urunYok = findViewById(R.id.txtUrunYok);
        urunYok.setVisibility(View.GONE);
        listeArray = new ArrayList<>();
        images = new ArrayList<>();
        catagoryId = getIntent().getStringExtra("CatagoryId");
        productPresenter = new ProductPresenter(this);
        productPresenter.fetchProduct();
    }

    @Override
    public void onFetchSuccess(List<Bilgiler> bilgilers) {

        String thumb = null;
        for (int i = 0; i < bilgilers.size(); i++) {
            List<Category> categories = bilgilers.get(i).getCategories();
            for (int k = 0; k < categories.size(); k++)
                if (categories.get(k).getCategoryId().equals(catagoryId)) {
                    String productId = bilgilers.get(i).getProductId();
                    String productName = bilgilers.get(i).getProductName();
                    String description = bilgilers.get(i).getDescription();
                    String price = bilgilers.get(i).getPrice();
                    boolean imgDurum = bilgilers.get(i).getImage();

                    if (imgDurum) {
                        for (int r = 0; r < bilgilers.get(i).getImages().size(); r++) {
                            thumb = bilgilers.get(i).getImages().get(r).getThumb();
                        }

                    } else {
                        thumb = "http://vincinmerkezi.com/images/resimyok.gif";
                    }

                    Products ur = new Products(productId, productName, description, price, thumb);
                    listeArray.add(ur);

                }
        }
        ProductAdapter adp = new ProductAdapter(ProductActivity.this, listeArray);
        ls.setAdapter(adp);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent i = new Intent(ProductActivity.this, ProductDetailActivity.class);
                    i.putExtra("image", listeArray.get(position).getImages());
                    i.putExtra("description", listeArray.get(position).getDescription());
                    i.putExtra("price", listeArray.get(position).getPrice());
                    i.putExtra("productName", listeArray.get(position).getProductName());
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ID yakalama hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onFetchFail() {
        Toast.makeText(this, "onFetchFail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProcessStart() {

    }

    @Override
    public void onProcessStop() {

    }
}