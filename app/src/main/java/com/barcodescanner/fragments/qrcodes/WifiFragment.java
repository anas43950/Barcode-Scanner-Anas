package com.barcodescanner.fragments.qrcodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentWifiBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.Wifi;

public class WifiFragment extends BaseFragment {
    FragmentWifiBinding binding;

    public WifiFragment() {
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
        binding = FragmentWifiBinding.inflate(inflater);
        initializeSpinner();
        return binding.getRoot();
    }

    private void initializeSpinner() {
        String[] encryptionType = new String[]{"WPA", "WEP", "None"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner_dropdown, encryptionType);
        binding.encryptionTypeAutocompleteTv.setAdapter(spinnerAdapter);
        binding.encryptionTypeAutocompleteTv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    binding.passwordEtContainer.setVisibility(View.GONE);
                } else {
                    binding.passwordEtContainer.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String networkName = binding.networkNameEt.getText().toString().trim();
            String encryptionType = binding.encryptionTypeAutocompleteTv.getText().toString().trim();
            String password = binding.passwordEt.getText().toString().trim();
            if (encryptionType.equals("")) {
                binding.encryptionTypeAutocompleteTv.setError("Select an encryption type");
                return false;
            }
            if (networkName.equals("")) {
                binding.networkNameEt.setError("Network name cannot be empty");
                return false;
            }
            Wifi wifi = new Wifi(networkName, encryptionType, password);
            createBarcodeActivity.createBarcode(wifi.toSchema());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}