package com.kkokate.shoppingapp.pages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kkokate.shoppingapp.R;
import com.kkokate.shoppingapp.adapter.CategoryListLayoutAdapter;
import com.kkokate.shoppingapp.service.api.Api;
import com.kkokate.shoppingapp.service.api.BaseApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDisplayPage extends AppCompatActivity implements View.OnClickListener {

    protected List<String> categoryList;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_display);
        initView();
        getTheListOfCategory();
    }

    private void getTheListOfCategory() {
        try{
            Api call = BaseApi.getInstance().create(Api.class);
            Call<List<String>> category = call.getCategoryList();
            category.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                    categoryList= response.body();
                    if(categoryList!=null){
                        CategoryListLayoutAdapter adapter = new CategoryListLayoutAdapter(CategoryDisplayPage.this,R.id.categoryListView,categoryList);
                        listView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    call.cancel();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initView() {
        listView = findViewById(R.id.categoryListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textViewItem = view.findViewById(R.id.category);
                String listItemText = textViewItem.getText().toString();

                Intent intent = new Intent(CategoryDisplayPage.this,ProductsDisplayPage.class);
                intent.putExtra("CATEGORY",listItemText);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        //ToDo add listView
    }


}
