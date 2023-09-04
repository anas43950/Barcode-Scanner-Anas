package com.barcodescanner.fragments.barcodes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentCode93Binding;
import com.barcodescanner.fragments.BaseFragment;

import java.util.regex.Pattern;

public class Code93Fragment extends BaseFragment {
    FragmentCode93Binding binding;
    public Code93Fragment() {
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
        binding = FragmentCode93Binding.inflate(inflater);
        binding.textEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Check if the text contains any special characters using regex
                String inputText = editable.toString();
                String regex = "^[A-Z0-9\\s]*$"; // Allow letters, digits, and spaces only
                if (!Pattern.matches(regex, inputText)) {
                    // If the text contains special characters, remove them
                    String filteredText = inputText.replaceAll("[^A-Z0-9\\s]", "");
                    binding.textEt.setText(filteredText);
                    binding.textEt.setSelection(filteredText.length());
                }

            }
        });
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