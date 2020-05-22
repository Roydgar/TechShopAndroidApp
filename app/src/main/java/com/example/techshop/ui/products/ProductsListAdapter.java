package com.example.techshop.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techshop.R;
import com.example.techshop.repository.Repository;
import com.example.techshop.repository.entities.Product;

public class ProductsListAdapter extends ListAdapter<Product, ProductsListAdapter.MyViewHolder> {

    public ProductsListAdapter() {
        super(Product.DIFF_CALLBACK);
    }

    @Override
    public ProductsListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private Product product;
        private TextView itemName;
        private TextView itemPrice;
        private Button infoBtn;
        private Button buyBtn;

        public MyViewHolder(View v) {
            super(v);

            itemName = v.findViewById(R.id.productItemName);
            itemPrice = v.findViewById(R.id.productItemPrice);
            infoBtn = v.findViewById(R.id.productItemInfoBtn);
            buyBtn = v.findViewById(R.id.productItemBuyBtn);
        }

        public void bindTo(Product product) {
            this.product = product;
            itemName.setText(product.getName());
            itemPrice.setText("$ " + String.valueOf(product.getPrice()));

            infoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("productId", product.getId());
                    Navigation.findNavController(view).navigate(R.id.navigation_product_info, bundle);
                }
            });

            buyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Repository.getInstance().addProductToCart(product);
                }
            });
        }
    }
}
