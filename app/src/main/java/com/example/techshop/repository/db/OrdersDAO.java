package com.example.techshop.repository.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.techshop.repository.entities.Order;

import java.util.List;

@Dao
public interface OrdersDAO {

    @Query("SELECT * FROM `Order`")
    LiveData<List<Order>> getAll();

    @Query("SELECT * FROM `Order` WHERE id = :id ")
    LiveData<Order> getById(Long id);

    @Insert
    long insert(Order order);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Order> orders);
}
