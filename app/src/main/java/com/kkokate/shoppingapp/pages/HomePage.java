package com.kkokate.shoppingapp.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kkokate.shoppingapp.R;
import com.kkokate.shoppingapp.adapter.HomePageProductListAdapter;
import com.kkokate.shoppingapp.model.Products;
import com.kkokate.shoppingapp.service.api.Api;
import com.kkokate.shoppingapp.service.api.BaseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity implements View.OnClickListener{

    protected List<Products> productsList;
    protected EditText searchBar;
    protected TextView listBar;
    protected GridView gridView;
    protected Spinner categorySpinner;

    protected String[] categoryArray =new String[]{ "All category","electronics",
            "jewelery",
            "men's clothing",
            "women's clothing"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_layout);
        initUi();
    }

    private void getTheProductList(String category) {
        try{
            Api api = BaseApi.getInstance().create(Api.class);
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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Products product = productsList.get(i);

                Intent intent = new Intent(HomePage.this,ProductsDetailPage.class);
                intent.putExtra("PRODUCT", product);
                startActivity(intent);
            }
        });

            categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                    new String[]{ "All category","electronics",
                            "jewelery",
                            "men's clothing",
                            "women's clothing"});
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorySpinner.setAdapter(adapter);
            categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String category=categoryArray[i];
                    if(!category.equals("All category")) {
                        getTheProductList(category);
                    }else{
                        getTheProductList(null);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    finish();
                }
            });
    }

    @Override
    public void onClick(View view) {
        
    }
}
