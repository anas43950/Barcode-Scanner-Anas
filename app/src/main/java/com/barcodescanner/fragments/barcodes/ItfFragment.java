package com.barcodescanner.fragments.barcodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentItfBinding;
import com.barcodescanner.fragments.BaseFragment;

public class ItfFragment extends BaseFragment {
    FragmentItfBinding binding;

    public ItfFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItfBinding.inflate(inflater);
        return binding.getRoot();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String text = binding.textEt.getText().toString().trim();
            if (text.isEmpty()) {
                binding.textEt.setError("Cannot be empty");
                return false;
            }
            if (text.length() % 2 != 0) {
                binding.textEt.setError("Number of digits should be even");
                return false;
            }
            createBarcodeActivity.createBarcode(text);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}