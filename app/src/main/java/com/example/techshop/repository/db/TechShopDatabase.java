package com.example.techshop.repository.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.techshop.repository.entities.Order;
import com.example.techshop.repository.entities.Product;

@Database(entities = {Product.class, Order.class}, version = 1, exportSchema = false)
public abstract class TechShopDatabase extends RoomDatabase {

    private static final String PRODUCTS_DB_NAME = "tech_shop.db";

    private static TechShopDatabase instance;

    public static TechShopDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (TechShopDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            TechShopDatabase.class, PRODUCTS_DB_NAME).build();
                }
            }
        }
        return instance;
    }

    public abstract ProductsDAO productsDAO();
    public abstract OrdersDAO   ordersDAO();
}
