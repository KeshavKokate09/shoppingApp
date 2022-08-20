package com.kkokate.shoppingapp.service.api;

import com.kkokate.shoppingapp.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiCall {
    @GET("products/categories")
    Call<List<String>> getCategoryList();

    @GET("products")
    Call<List<Products>> getProductsList();

    @GET("products/category/{category}")
    Call<List<Products>> getProductsListByCategory(@Path("category") String category);
}
