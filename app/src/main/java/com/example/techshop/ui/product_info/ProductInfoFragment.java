package com.example.techshop.ui.product_info;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.techshop.R;
import com.example.techshop.repository.entities.Product;

public class ProductInfoFragment extends Fragment {

    private ProductInfoViewModel mViewModel;

    private TextView productName;
    private TextView productPrice;
    private TextView productDescription;
    private TextView productCategory;
    private TextView productAvailability;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = ViewModelProviders.of(this).get(ProductInfoViewModel.class);

        long productId = getArguments().getLong("productId");
        mViewModel.setProductId(productId);

        View v = inflater.inflate(R.layout.fragment_product_info, container, false);

        productName = v.findViewById(R.id.productItemName);
        productPrice = v.findViewById(R.id.productItemPrice);
        productDescription = v.findViewById(R.id.productItemDescription);
        productCategory = v.findViewById(R.id.productItemCategory);
        productAvailability = v.findViewById(R.id.productItemAvailability);

        mViewModel.getProduct().observe(getViewLifecycleOwner(), new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                productName.setText(product.getName());
                productPrice.setText("$ " + product.getPrice());
                productDescription.setText("Description:\n" + product.getDescription());
                productCategory.setText("Category: " + product.getCategory());
                productAvailability.setText("Availability: " + (product.getAvailable() ? "available" : "not available"));
            }
        });

        return v;
    }

}
