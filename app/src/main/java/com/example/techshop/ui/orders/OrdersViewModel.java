package com.example.techshop.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.techshop.repository.Repository;
import com.example.techshop.repository.entities.Order;

import java.util.List;

public class OrdersViewModel extends ViewModel {

    public LiveData<List<Order>> getOrdersList() {
        return Repository.getInstance().getOrdersList();
    }

    public void refreshOrdersList() {
        Repository.getInstance().refreshOrdersList();
    }
}
