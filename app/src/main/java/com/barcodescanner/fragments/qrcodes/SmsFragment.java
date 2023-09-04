package com.barcodescanner.fragments.qrcodes;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentSmsBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.Sms;

public class SmsFragment extends BaseFragment {
    FragmentSmsBinding binding;

    public SmsFragment() {
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
        binding = FragmentSmsBinding.inflate(inflater);
        binding.phoneNoContainer.setEndIconOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                openContactPicker(i);
            }
        });
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String phoneNumber = binding.phoneNoEt.getText().toString().trim();
            String message = binding.messageEt.getText().toString().trim();
            if (phoneNumber.isEmpty()) {
                binding.phoneNoEt.setError("Enter phone number");
                return false;
            }
            createBarcodeActivity.createBarcode(new Sms(phoneNumber, message).toSchema());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onContactSelected(Uri uri) {
        super.onContactSelected(uri);
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = createBarcodeActivity.getContentResolver().query(uri, projection, null, null, null);

        // If the cursor returned is valid, get the phone number
        if (cursor != null && cursor.moveToFirst()) {
            int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String number = cursor.getString(numberIndex);
            binding.phoneNoEt.setText(number);
        }
        if (cursor != null) {
            cursor.close();
        }
    }


}