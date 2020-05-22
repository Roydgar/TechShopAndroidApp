package com.example.techshop.ui.products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.techshop.repository.Repository;
import com.example.techshop.repository.entities.Product;

import java.util.List;

public class ProductsViewModel extends ViewModel {

    public LiveData<List<Product>> getProductsList() {
        return Repository.getInstance().getProductsList();
    }

    public void refreshProductsList() {
        Repository.getInstance().refreshProductsList();
    }
}