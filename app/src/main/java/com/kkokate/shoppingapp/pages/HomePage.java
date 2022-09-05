package com.kkokate.shoppingapp.pages;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kkokate.shoppingapp.R;
import com.kkokate.shoppingapp.adapter.HomePageProductListAdapter;
import com.kkokate.shoppingapp.adapter.ProductsListLayoutAdapter;
import com.kkokate.shoppingapp.model.Products;
import com.kkokate.shoppingapp.service.api.Api;
import com.kkokate.shoppingapp.service.api.BaseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    protected List<Products> productsList;
    protected EditText searchBar;
    protected TextView listBar;
    protected GridView gridView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_layout);
        getTheProductList();
        initUi();
    }

    private void getTheProductList() {
        try{
            Api api = BaseApi.getInstance().create(Api.class);
            Call<List<Products>> call = null;
            call =api.getProductsList();
            call.enqueue(new Callback<List<Products>>() {
                @Override
                public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                    productsList =response.body();
                    if(productsList!=null){
                        HomePageProductListAdapter adapter = new HomePageProductListAdapter(HomePage.this,R.layout.list_products_horizontal,productsList);
                        gridView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<Products>> call, Throwable t) {
                    call.cancel();
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void initUi() {
        searchBar = findViewById(R.id.searchBar);
        listBar = findViewById(R.id.listTextView);
        listBar.setOnClickListener(this);
        gridView = findViewById(R.id.productsGridViewHomePage);
    }

    @Override
    public void onClick(View view) {
        
    }
}
