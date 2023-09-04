package com.barcodescanner.fragments.qrcodes;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentEmailBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.Email;


public class EmailFragment extends BaseFragment {
    FragmentEmailBinding binding;

    public EmailFragment() {
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
        binding = FragmentEmailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String email = binding.emailEt.getText().toString().trim();
            String subject = binding.subjectEt.getText().toString().trim();
            String message = binding.messageEt.getText().toString().trim();
            if (email.isEmpty()) {
                binding.emailEt.setError("Email cannot be empty");
                return false;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEt.setError("Enter a valid email");
                return false;
            }
            createBarcodeActivity.createBarcode(new Email(email, subject, message).toSchema());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}