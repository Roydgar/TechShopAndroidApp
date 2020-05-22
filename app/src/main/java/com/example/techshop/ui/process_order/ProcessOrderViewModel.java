package com.example.techshop.ui.process_order;

import androidx.lifecycle.ViewModel;

import com.example.techshop.repository.Repository;

public class ProcessOrderViewModel extends ViewModel {

    public void confirm(String userName, String userEmail) {
        Repository.getInstance().getCartOrder().setConsumerName(userName);
        Repository.getInstance().getCartOrder().setConsumerEmail(userEmail);
        Repository.getInstance().postOrder();
    }
}
