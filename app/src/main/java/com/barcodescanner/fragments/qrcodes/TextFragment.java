package com.barcodescanner.fragments.qrcodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentTextBinding;
import com.barcodescanner.fragments.BaseFragment;


public class TextFragment extends BaseFragment {
    FragmentTextBinding binding;

    public TextFragment() {
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
        binding = FragmentTextBinding.inflate(inflater);
        binding.textEt.requestFocus();
        return binding.getRoot();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            if (binding.textEt.getText().toString().trim().length() == 0) {
                Toast.makeText(createBarcodeActivity, "Text cannot be empty", Toast.LENGTH_SHORT).show();
                return false;
            }
            createBarcodeActivity.createBarcode(binding.textEt.getText().toString().trim());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}