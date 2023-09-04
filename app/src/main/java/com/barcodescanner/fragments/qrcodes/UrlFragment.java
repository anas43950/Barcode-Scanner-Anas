package com.barcodescanner.fragments.qrcodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentUrlBinding;
import com.barcodescanner.fragments.BaseFragment;

public class UrlFragment extends BaseFragment {
    FragmentUrlBinding binding;

    public UrlFragment() {
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
        binding = FragmentUrlBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String url = binding.urlEt.getText().toString().trim();
            if (url.length() == 0) {
                Toast.makeText(createBarcodeActivity, "URL cannot be empty", Toast.LENGTH_SHORT).show();
                return false;
            }

            createBarcodeActivity.createBarcode(url);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}