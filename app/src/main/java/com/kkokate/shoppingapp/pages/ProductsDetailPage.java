package com.kkokate.shoppingapp.pages;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kkokate.shoppingapp.R;
import com.kkokate.shoppingapp.model.Products;

public class ProductsDetailPage extends AppCompatActivity {

    protected Products product;
    protected TextView prodTitle;
    protected TextView prodPrice;
    protected TextView prodCategory;
    protected TextView prodDesc;
    protected ImageView prodImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_page);
        product=(Products) getIntent().getSerializableExtra("PRODUCT");
        initView();
    }

    private void initView() {
        prodImage= findViewById(R.id.productImage);
        Glide.with(this)
                .load(product.getImage())
                .into(prodImage);
        prodTitle = findViewById(R.id.productTitleText);
        prodTitle.setText(product.getTitle());
        prodCategory = findViewById(R.id.productCategoryText);
        prodCategory.setText(product.getCategory());
        prodPrice = findViewById(R.id.productPriceText);
        prodPrice.setText("$ "+product.getPrice());
        prodDesc = findViewById(R.id.productDescText);
        prodDesc.setText(product.getDescription());


    }
}
