package com.example.techshop.repository.converter;

import androidx.room.TypeConverter;

import com.example.techshop.repository.entities.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class OrderProductsConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Product> stringToProductsList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Product>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String integerListToString(List<Product> someObjects) {
        return gson.toJson(someObjects);
    }
}
