package com.example.techshop.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.techshop.R;

public class ProductsFragment extends Fragment {
    private static final String TAG = ProductsFragment.class.getSimpleName();

    private ProductsViewModel productsViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProductsListAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productsViewModel =
                ViewModelProviders.of(this).get(ProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_products, container, false);

        initViews(root);
        initViewModel();

        refreshProductsList();

        return root;
    }

    private void initViews(View root) {
        initRecyclerView(root);
        initSwipeRefreshView(root);
    }

    private void initRecyclerView(View root) {
        recyclerView = root.findViewById(R.id.productsListRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ProductsListAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void initSwipeRefreshView(View root) {
        swipeRefreshLayout = root.findViewById(R.id.productsListSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshProductsList();
            }
        });
    }

    private void refreshProductsList() {
        productsViewModel.refreshProductsList();
    }

    private void initViewModel() {
        productsViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);

        productsViewModel.getProductsList().observe(getViewLifecycleOwner(), productsList -> {
            mAdapter.submitList(productsList);
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}