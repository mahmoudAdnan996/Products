package com.apps.madnan.products.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.madnan.products.R;
import com.apps.madnan.products.model.Product;

import java.util.ArrayList;

/**
 * Created by mahmoud adnan on 12/7/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder>{

    ArrayList<Product> products;
    Context context;

    public ProductAdapter(ArrayList<Product> movies) {
        this.products = movies;
    }

    public ProductAdapter(ArrayList<Product> movies, Context context) {
        this.products = movies;
        this.context = context;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.setProductView(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
