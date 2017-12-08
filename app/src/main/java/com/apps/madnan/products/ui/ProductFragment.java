package com.apps.madnan.products.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.madnan.products.R;
import com.apps.madnan.products.model.Product;
import com.apps.madnan.products.service.ProductService;
import com.apps.madnan.products.ui.adapter.ProductAdapter;
import com.apps.madnan.products.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.apps.madnan.products.service.Constants.BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    @BindView(R.id.productRV) RecyclerView productsRV;

    ProductAdapter productAdapter;

    int from = 1;

    ArrayList<Product> productArrayList;

    public ProductFragment() {
    }

    public static ProductFragment newInstance() {

        return new ProductFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        productArrayList = new ArrayList<>();

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        productsRV.setLayoutManager(staggeredGridLayoutManager);
        productsRV.setItemAnimator(new DefaultItemAnimator());

        productAdapter = new ProductAdapter(productArrayList, getContext());
        productsRV.setAdapter(productAdapter);

        productsRV.addOnScrollListener(new EndlessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                from = from + 10;
                getProductFrom(from);
            }
        });

        getProductFrom(from);
    }

    private void getProductFrom(final int fromNum) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductService productService = retrofit.create(ProductService.class);
        Call<ArrayList<Product>> connection = productService.getProducts(fromNum);
        connection.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
               productArrayList.addAll(response.body());
               productAdapter.notifyItemRangeInserted(productAdapter.getItemCount(), productArrayList.size()-1);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.e("Error is", t.getMessage());
            }
        });
    }


}
