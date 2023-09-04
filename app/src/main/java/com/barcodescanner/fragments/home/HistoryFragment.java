package com.barcodescanner.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.barcodescanner.adapters.HistoryAdapter;
import com.barcodescanner.databinding.FragmentHistoryBinding;
import com.barcodescanner.models.Barcode;
import com.barcodescanner.utils.AdUtils;
import com.barcodescanner.utils.database.BarcodesDatabase;

import java.util.Collections;
import java.util.List;

public class HistoryFragment extends Fragment {
    FragmentHistoryBinding binding;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdUtils.showInterstitialAd(getActivity());
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getActivity().setTitle("History");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater);
        binding.historyRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        BarcodesDatabase barcodesDatabase = BarcodesDatabase.getInstance(getActivity());
        List<Barcode> barcodes = barcodesDatabase.barcodesDao().loadAllHistoryItems();
        Collections.reverse(barcodes);
        binding.historyRv.setAdapter(new HistoryAdapter(getActivity(), barcodes));
    }
}