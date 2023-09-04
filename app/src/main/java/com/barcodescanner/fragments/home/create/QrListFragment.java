package com.barcodescanner.fragments.home.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.barcodescanner.adapters.CreateQrBarcodeListAdapter;
import com.barcodescanner.databinding.FragmentQrCodesListBinding;
import com.barcodescanner.models.CreateQrBarcodeListModel;

public class QrListFragment extends Fragment {
    FragmentQrCodesListBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQrCodesListBinding.inflate(inflater);
        binding.createQrCodesRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.createQrCodesRv.setAdapter(new CreateQrBarcodeListAdapter(getActivity(), CreateQrBarcodeListModel.getQrListItems()));
        return binding.getRoot();
    }

}