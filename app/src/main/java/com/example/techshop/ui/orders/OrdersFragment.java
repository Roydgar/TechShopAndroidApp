package com.example.techshop.ui.orders;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.techshop.R;

public class OrdersFragment extends Fragment {
    private static final String TAG = OrdersFragment.class.getSimpleName();

    private OrdersViewModel ordersViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OrdersListAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ordersViewModel =
                ViewModelProviders.of(this).get(OrdersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        initViews(root);
        initViewModel();

        refreshOrdersList();

        return root;
    }

    private void initViews(View root) {
        initRecyclerView(root);
        initSwipeRefreshView(root);
    }

    private void initRecyclerView(View root) {
        recyclerView = root.findViewById(R.id.ordersListRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new OrdersListAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void initSwipeRefreshView(View root) {
        swipeRefreshLayout = root.findViewById(R.id.ordersListSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshOrdersList();
            }
        });
    }

    private void refreshOrdersList() {
        ordersViewModel.refreshOrdersList();
    }

    private void initViewModel() {
        ordersViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);

        ordersViewModel.getOrdersList().observe(getViewLifecycleOwner(), ordersList -> {
            mAdapter.submitList(ordersList);
            swipeRefreshLayout.setRefreshing(false);
        });
    }

}
