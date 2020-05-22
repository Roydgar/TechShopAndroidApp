package com.example.techshop;

import android.app.Application;

import com.example.techshop.repository.Repository;
import com.example.techshop.repository.db.TechShopDatabase;

public class TechShopApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TechShopDatabase techShopDatabase = TechShopDatabase.getDatabase(this);
        Repository.getInstance().setTechShopDatabase(techShopDatabase);
    }
}
