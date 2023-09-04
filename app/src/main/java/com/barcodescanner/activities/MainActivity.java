package com.barcodescanner.activities;

import android.Manifest.permission;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.barcodescanner.R;
import com.barcodescanner.databinding.ActivityMainBinding;
import com.barcodescanner.fragments.home.HistoryFragment;
import com.barcodescanner.fragments.home.QrBarcodeListFragment;
import com.barcodescanner.fragments.home.ScannerFragment;
import com.barcodescanner.fragments.home.SettingsFragment;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;


public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    Fragment currentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot(), this, "Scan", true);
        getSupportActionBar().hide();

        if (checkSelfPermission(permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            askCameraPermission();
        } else {
            loadFragment(new ScannerFragment());
        }
        binding.bottomNav.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_scan) {
                    loadFragment(new ScannerFragment());
                } else if (id == R.id.menu_create) {
                    loadFragment(new QrBarcodeListFragment());
                } else if (id == R.id.menu_history) {
                    loadFragment(new HistoryFragment());
                } else if (id == R.id.menu_settings) {
                    loadFragment(new SettingsFragment());
                }
                return true;
            }
        });
    }

    @Override
    protected void loadFragment(Fragment fragment) {
        super.loadFragment(fragment);
        currentFragment = fragment;
    }

    private void askCameraPermission() {
        ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new RequestPermission(), isGranted -> {
            if (!isGranted) {
                Toast.makeText(this, "Camera permission required", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                loadFragment(new ScannerFragment());
            }
        });
        requestPermissionLauncher.launch(permission.CAMERA);
    }

    @Override
    public boolean onSupportNavigateUp() {
        binding.bottomNav.setSelectedItemId(R.id.menu_scan);
        loadFragment(new ScannerFragment());
        return true;
    }
}