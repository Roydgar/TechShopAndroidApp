package com.example.techshop.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.techshop.repository.db.TechShopDatabase;
import com.example.techshop.repository.entities.Order;
import com.example.techshop.repository.entities.Product;
import com.example.techshop.repository.remote.RemoteEndpoints;
import com.example.techshop.repository.remote.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private TechShopDatabase techShopDatabase;
    RemoteEndpoints service = RetrofitClientInstance.getRetrofitInstance().create(RemoteEndpoints.class);


    public void setTechShopDatabase(TechShopDatabase techShopDatabase) {
        this.techShopDatabase = techShopDatabase;
    }


    // Products
    public LiveData<List<Product>> getProductsList() {
        return techShopDatabase.productsDAO().getAll();
    }

    public LiveData<Product> getProductById(long id) {
        return techShopDatabase.productsDAO().getById(id);
    }

    public void refreshProductsList() {
        Call<List<Product>> call = service.getProductsList();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.i("Repository", "onResponse: " + response.toString());
                if (response.isSuccessful()) {
                    new Thread(()-> techShopDatabase.productsDAO().insertAll(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Repository", "onFailure:" + t.toString());
            }
        });
    }


    // Orders
    public LiveData<List<Order>> getOrdersList() {
        return techShopDatabase.ordersDAO().getAll();
    }

    public void refreshOrdersList() {
        Call<List<Order>> call = service.getOrdersList();
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                Log.i("Repository", "onResponse: " + response.toString());
                if (response.isSuccessful()) {
                    new Thread(()-> techShopDatabase.ordersDAO().insertAll(response.body())).start();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("Repository", "onFailure:" + t.toString());
            }
        });
    }

    // Cart
    private MutableLiveData<Order> cartOrderLiveData = new MutableLiveData<>();
    private Order cartOrder = new Order();

    public void addProductToCart(Product product){
        cartOrder.addProduct(product);
    }

    public void removeCartProduct(long productId) {
        cartOrder.removeProduct(productId);
        cartOrderLiveData.setValue(cartOrder);
    }

    public LiveData<Order> getCartOrderLiveData() {
        cartOrderLiveData.setValue(cartOrder);
        return cartOrderLiveData;
    }

    public Order getCartOrder() {
        return cartOrder;
    }

    public void postOrder() {
        Call<Order> call = service.createOrder(this.cartOrder);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                Log.i("Repository", "onResponse: " + response.toString());
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Repository", "onFailure:" + t.toString());
            }
        });
    }

    private static Repository instance;

    public static Repository getInstance(){
        if(instance == null){
            instance = new Repository();
        }
        return instance;
    }
}
