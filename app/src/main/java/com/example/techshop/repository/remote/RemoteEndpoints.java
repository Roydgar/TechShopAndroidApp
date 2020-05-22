package com.example.techshop.repository.remote;

import com.example.techshop.repository.entities.Order;
import com.example.techshop.repository.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RemoteEndpoints {

    @GET("products")
    Call<List<Product>> getProductsList();

    @GET("orders")
    Call<List<Order>> getOrdersList();

    @POST("orders")
    Call<Order> createOrder(@Body Order order);
}
