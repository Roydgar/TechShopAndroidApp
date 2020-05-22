package com.example.techshop.ui.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techshop.R;
import com.example.techshop.repository.entities.Order;

public class OrdersListAdapter extends ListAdapter<Order, OrdersListAdapter.MyViewHolder> {

    public OrdersListAdapter() {
        super(Order.DIFF_CALLBACK);
    }

    @Override
    public OrdersListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);

        OrdersListAdapter.MyViewHolder vh = new OrdersListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(OrdersListAdapter.MyViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private Order order;
        private TextView orderTitle;
        private TextView orderConsumerName;
        private TextView orderConsumerEmail;
        private TextView orderPrice;

        public MyViewHolder(View v) {
            super(v);

            orderTitle = v.findViewById(R.id.orderItemTitle);
            orderConsumerName = v.findViewById(R.id.orderItemConsumerName);
            orderConsumerEmail = v.findViewById(R.id.orderItemConsumerEmail);
            orderPrice = v.findViewById(R.id.orderItemPrice);
        }

        public void bindTo(Order order) {
            this.order = order;
            orderTitle.setText("Order #" + order.getId());
            orderConsumerName.setText("Name: " + order.getConsumerName());
            orderConsumerEmail.setText("Email: " + order.getConsumerEmail());
            orderPrice.setText("Price: " + order.getTotalPrice());
        }
    }
}
