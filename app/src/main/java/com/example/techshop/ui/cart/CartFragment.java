package com.example.techshop.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.techshop.R;
import com.example.techshop.ui.products.ProductsListAdapter;
import com.example.techshop.ui.products.ProductsViewModel;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
//    private SwipeRefreshLayout swipeRefreshLayout;
    private CartProductsListAdapter mAdapter;

    private TextView totalCartOrderPrice;
    private Button checkoutBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel =
                ViewModelProviders.of(this).get(CartViewModel.class);

        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        initViews(root);
        initViewModel();

//        refreshProductsList();

        return root;
    }

    private void initViews(View root) {
        initRecyclerView(root);
//        initSwipeRefreshView(root);

        totalCartOrderPrice = root.findViewById(R.id.totalCartOrderPrice);
        checkoutBtn = root.findViewById(R.id.checkoutBtn);

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigation_process_order);
            }
        });
    }

    private void initRecyclerView(View root) {
        recyclerView = root.findViewById(R.id.productsListRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CartProductsListAdapter();
        recyclerView.setAdapter(mAdapter);
    }

//    private void initSwipeRefreshView(View root) {
//        swipeRefreshLayout = root.findViewById(R.id.productsListSwipeRefreshLayout);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshProductsList();
//            }
//        });
//    }

    private void initViewModel() {
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        cartViewModel.getCartOrder().observe(getViewLifecycleOwner(), cartOrder -> {
            mAdapter.submitList(cartOrder.getProducts());
            mAdapter.notifyDataSetChanged();
            totalCartOrderPrice.setText("Total price: " + cartOrder.getTotalPrice());
        });
    }
}