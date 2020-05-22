package com.example.techshop.ui.product_info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.techshop.repository.Repository;
import com.example.techshop.repository.entities.Product;

public class ProductInfoViewModel extends ViewModel {
    private LiveData<Product> mProduct;

    public ProductInfoViewModel() {

    }

    public LiveData<Product> getProduct() {
        return mProduct;
    }

    public void setProductId(long id) {
        mProduct = Repository.getInstance().getProductById(id);
    }
}
