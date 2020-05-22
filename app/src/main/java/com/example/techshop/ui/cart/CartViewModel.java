package com.example.techshop.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.techshop.repository.Repository;
import com.example.techshop.repository.entities.Order;
import com.example.techshop.repository.entities.Product;

import java.util.List;

public class CartViewModel extends ViewModel {

//    private MutableLiveData<String> mText;
//
//    public CartViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is cart fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }

    public LiveData<Order> getCartOrder() {
        return Repository.getInstance().getCartOrderLiveData();
    }
}