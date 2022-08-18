package com.kkokate.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kkokate.shoppingapp.pages.CategoryDisplayPage;
import com.kkokate.shoppingapp.pages.ProductsDisplayPage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button productsBtn;
    protected Button categoryBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        productsBtn = findViewById(R.id.prodButton);
        productsBtn.setOnClickListener(this);
        categoryBtn = findViewById(R.id.categoryButton);
        categoryBtn.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View view){
        if(productsBtn.equals(view)){
         goToProductsPage();   
        }else if(categoryBtn.equals(view)){
            goToCategoryPage();
        }
    }

    private void goToCategoryPage() {
        Intent intent = new Intent(this, CategoryDisplayPage.class);
        startActivity(intent);
    }

    private void goToProductsPage() {
        Intent intent = new Intent(this, ProductsDisplayPage.class);
        startActivity(intent);
    }
}