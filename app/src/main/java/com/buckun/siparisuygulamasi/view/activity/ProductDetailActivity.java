package com.buckun.siparisuygulamasi.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.buckun.siparisuygulamasi.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    String image;
    String description;
    String price;
    String productName;
    TextView descriptionText;
    ImageView imageView;
    Button orderProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        image = getIntent().getStringExtra("image");
        description = getIntent().getStringExtra("description");
        price = getIntent().getStringExtra("price");
        productName = getIntent().getStringExtra("productName");

        descriptionText = findViewById(R.id.txtBilgi);
        imageView = findViewById(R.id.imageView);
        orderProduct = findViewById(R.id.btnSiparis);
        descriptionText.setText(description);
        Picasso.get().load(image).into(imageView);

        orderProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Sipariş alındı: ödenecek miktar " + price, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}