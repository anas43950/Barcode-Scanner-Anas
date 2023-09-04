package com.barcodescanner.activities;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.barcodescanner.databinding.ActivityPreviewBinding;
import com.barcodescanner.models.Barcode;
import com.barcodescanner.utils.Constants;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PreviewActivity extends BaseActivity {
    ActivityPreviewBinding binding;
    Barcode barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot(), this, "Preview", true);
        barcode = (Barcode) getIntent().getSerializableExtra(Constants.BARCODE);
        binding.contentTv.setText(barcode.getTextContent());
        generateBarcode();
        binding.copyBtn.setOnClickListener(view -> copyToClipboard(binding.contentTv.getText().toString()));
    }

    private void generateBarcode() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                MultiFormatWriter writer = new MultiFormatWriter();
                int dim = 900;
                try {
                    BitMatrix bm = writer.encode(barcode.getTextContent(), barcode.getBarcodeFormat(), dim, dim);
                    Bitmap bitmap = Bitmap.createBitmap(dim, dim, Config.ARGB_8888);
                    for (int i = 0; i < bm.getWidth(); i++) {//width
                        for (int j = 0; j < bm.getHeight(); j++) {//height
                            bitmap.setPixel(i, j, bm.get(i, j) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.progressBar.setVisibility(View.GONE);
                            binding.barcodeIv.setVisibility(View.VISIBLE);
                            binding.barcodeIv.setImageBitmap(bitmap);
                        }
                    });
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}