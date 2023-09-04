package com.barcodescanner.fragments.qrcodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentBookmarkBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.Bookmark;

public class BookmarkFragment extends BaseFragment {
    FragmentBookmarkBinding binding;

    public BookmarkFragment() {
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
        binding = FragmentBookmarkBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String title = binding.titleEt.getText().toString().trim();
            String url = binding.urlEt.getText().toString().trim();
            if (title.isEmpty() && url.isEmpty()) {
                createBarcodeActivity.showToast("Provide title or URL");
                return false;
            }
            createBarcodeActivity.createBarcode(new Bookmark(title, url).toSchema());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}