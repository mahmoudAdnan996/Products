package com.apps.madnan.products.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.madnan.products.R;
import com.apps.madnan.products.model.Product;
import com.bumptech.glide.Glide;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mahmoud adnan on 12/7/2017.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.priceTV) TextView price;
    @BindView(R.id.descTV) TextView description;
    @BindView(R.id.productIMG) ImageView productImg;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void setProductView(Product productView){

        int height = productView.getImage().getHeight();
        productImg.requestLayout();
        productImg.getLayoutParams().height = height;

        Glide.with(itemView.getContext())
                .load(productView.getImage().getUrl()).into(productImg);

        String priceNumber = productView.getPrice();
        price.setText(NumberFormat.getCurrencyInstance().format(Integer.valueOf(priceNumber)));
        description.setText(productView.getProductDescription());

    }
}
