package com.apps.madnan.products.service;

import com.apps.madnan.products.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mahmoud adnan on 12/7/2017.
 */

public interface ProductService {

    @GET("products?count=10&from=1")
    Call<ArrayList<Product>> getProducts(@Query("from") int numFrom);
}
