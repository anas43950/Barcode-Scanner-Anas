package com.barcodescanner.fragments.barcodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentEan8Binding;
import com.barcodescanner.fragments.BaseFragment;

public class Ean8Fragment extends BaseFragment {

    FragmentEan8Binding binding;

    public Ean8Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEan8Binding.inflate(inflater);
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
            createBarcodeActivity.createBarcode(text);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}