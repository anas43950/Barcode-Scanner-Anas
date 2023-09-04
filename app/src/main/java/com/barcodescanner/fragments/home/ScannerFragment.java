package com.barcodescanner.fragments.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.barcodescanner.R;
import com.barcodescanner.activities.BaseActivity;
import com.barcodescanner.activities.PreviewActivity;
import com.barcodescanner.databinding.FragmentScannerBinding;
import com.barcodescanner.models.Barcode;
import com.barcodescanner.utils.AdUtils;
import com.barcodescanner.utils.Constants;
import com.barcodescanner.utils.database.BarcodesDatabase;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScannerFragment extends Fragment {
    CodeScanner scanner;
    FragmentScannerBinding binding;
    SharedPreferences prefs;

    public ScannerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScannerBinding.inflate(getLayoutInflater());
        scanner = new CodeScanner(getActivity(), binding.scannerView);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdUtils.loadBannerAd(binding.adView);
        setupScanner();
        setupZooming();
        setListeners();
    }

    private void setupScanner() {
        boolean enableFlash = prefs.getBoolean(getString(R.string.enable_flashlight_key), false);
        scanner.setFlashEnabled(enableFlash);
        scanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean shouldCopyToClipboard = prefs.getBoolean(getString(R.string.copy_to_clipboard_key), false);
                        if (shouldCopyToClipboard) {
                            ((BaseActivity) getActivity()).copyToClipboard(result.getText());
                        }
                        Barcode barcode = new Barcode(result.getTimestamp(), result.getText(), result.getBarcodeFormat(), false);
                        BarcodesDatabase barcodesDatabase = BarcodesDatabase.getInstance(getActivity());
                        barcodesDatabase.barcodesDao().addBarcode(barcode);
                        vibrateIfNeeded();
                        startActivity(new Intent(getActivity(), PreviewActivity.class).putExtra(Constants.BARCODE, barcode));
                    }
                });
            }
        });
        scanner.startPreview();
    }

    private void setupZooming() {
        binding.zoomSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                scanner.setZoom(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.zoomInBtn.setOnClickListener(view -> {

            binding.zoomSeekBar.setProgress(binding.zoomSeekBar.getProgress() + 5);
        });

        binding.zoomOutBtn.setOnClickListener(view -> {

            binding.zoomSeekBar.setProgress(binding.zoomSeekBar.getProgress() - 5);
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        scanner.stopPreview();
    }

    @Override
    public void onResume() {
        super.onResume();
        scanner.startPreview();
    }

    private void setListeners() {
        binding.flashBtn.setOnClickListener(view -> {
            scanner.setFlashEnabled(!scanner.isFlashEnabled());
            binding.flashIv.setImageResource(scanner.isFlashEnabled() ? R.drawable.ic_flash_off : R.drawable.ic_flash_on);
        });
    }

    private void vibrateIfNeeded() {
        boolean vibrate = prefs.getBoolean(getString(R.string.vibration_key), false);
        if (!vibrate) return;
        Vibrator v = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(10000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }

    }
}