package com.example.techshop.repository.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.techshop.repository.entities.Product;

import java.util.List;

@Dao
public interface ProductsDAO {

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAll();

    @Query("SELECT * FROM product WHERE id = :id ")
    LiveData<Product> getById(Long id);

    @Insert
    long insert(Product product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Product> products);
}
