package com.example.techshop.repository.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.techshop.repository.converter.OrderProductsConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @SerializedName("id")
    @Expose
    @PrimaryKey()
    @ColumnInfo(name = "id")
    private Long id;

    @SerializedName("consumerName")
    @Expose
    @ColumnInfo(name = "consumerName")
    private String consumerName;

    @SerializedName("consumerEmail")
    @Expose
    @ColumnInfo(name = "consumerEmail")
    private String consumerEmail;

    @SerializedName("products")
    @Expose
    @ColumnInfo(name = "products")
    @TypeConverters(OrderProductsConverter.class)
    private List<Product> products = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerEmail() {
        return consumerEmail;
    }

    public void setConsumerEmail(String consumerEmail) {
        this.consumerEmail = consumerEmail;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }
        this.products.add(product);
    }

    public void removeProduct(long productId) {
        if (this.products == null) {
            this.products = new ArrayList<>();
        }

        Product productToRemove = null;

        for (Product product : this.products) {
            if (product.getId() == productId) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            this.products.remove(productToRemove);
        }
    }

    public int getTotalPrice() {
        if (products == null) {
            return 0;
        }

        int result = 0;

        for (Product product : products) {
            result += product.getPrice();
        }

        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj != null && getClass() != obj.getClass()) {
            return false;
        }

        Order order = (Order) obj;
        if (order != null) {
            return this.id.equals(order.getId());
        }

        return false;
    }

    public static final DiffUtil.ItemCallback<Order> DIFF_CALLBACK = new DiffUtil.ItemCallback<Order>() {
        @Override
        public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            return oldItem.equals(newItem);
        }
    };
}
