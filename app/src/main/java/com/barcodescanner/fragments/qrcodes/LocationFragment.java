package com.barcodescanner.fragments.qrcodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentLocationBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.Location;


public class LocationFragment extends BaseFragment {
    FragmentLocationBinding binding;

    public LocationFragment() {
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
        binding = FragmentLocationBinding.inflate(inflater);

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.done_menu) {
            String latitude = binding.latitudeEt.getText().toString().trim();
            String longitude = binding.longitudeEt.getText().toString().trim();
            String altitude = binding.altitudeEt.getText().toString().trim();
            createBarcodeActivity.createBarcode(new Location(latitude, longitude, altitude).toSchema());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}