package com.barcodescanner.fragments.barcodes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentPdf417Binding;
import com.barcodescanner.fragments.BaseFragment;

public class Pdf417Fragment extends BaseFragment {
    FragmentPdf417Binding binding;
    public Pdf417Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPdf417Binding.inflate(inflater);
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