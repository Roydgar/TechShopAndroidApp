package com.example.techshop.ui.process_order;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.techshop.R;

public class ProcessOrderFragment extends Fragment {

    private ProcessOrderViewModel mViewModel;

    private EditText userNameEditText;
    private EditText userEmailEditText;
    private Button confirmBtn;

    public static ProcessOrderFragment newInstance() {
        return new ProcessOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ProcessOrderViewModel.class);
        View v = inflater.inflate(R.layout.fragment_process_order, container, false);

        userNameEditText = v.findViewById(R.id.userNameEditText);
        userEmailEditText = v.findViewById(R.id.userEmailEditText);
        confirmBtn = v.findViewById(R.id.confirmBtn);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.confirm(userNameEditText.getText().toString(),
                        userEmailEditText.getText().toString());

                Navigation.findNavController(view).navigate(R.id.navigation_orders);
            }
        });

        return v;
    }

}
