package com.kkokate.shoppingapp.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.kkokate.shoppingapp.R;
import com.kkokate.shoppingapp.adapter.ProductsListLayoutAdapter;
import com.kkokate.shoppingapp.model.Products;
import com.kkokate.shoppingapp.service.api.ApiCall;
import com.kkokate.shoppingapp.service.api.BaseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsDisplayPage extends AppCompatActivity {

    protected List<Products> productsList;
    protected String category;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display_page);
        category=getIntent().getStringExtra("CATEGORY");
        getTheProductList();
        initView();
    }

    private void getTheProductList() {
        try{
            ApiCall api = BaseApi.getInstance().create(ApiCall.class);
            Call<List<Products>> call = null;
            if(category==null){
               call =api.getProductsList();
            }else{
                call =api.getProductsListByCategory(category);
            }
            call.enqueue(new Callback<List<Products>>() {
                @Override
                public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                    productsList =response.body();
                    if(productsList!=null){
                        ProductsListLayoutAdapter adapter = new ProductsListLayoutAdapter(ProductsDisplayPage.this,R.layout.list_products,productsList);
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

    private void initView() {
       gridView = findViewById(R.id.productsGridView);
       gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Products product = productsList.get(i);

               Intent intent = new Intent(ProductsDisplayPage.this,ProductsDetailPage.class);
               intent.putExtra("PRODUCT", product);
               startActivity(intent);
           }
       });
    }

}
