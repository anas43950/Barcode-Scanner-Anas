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
import com.barcodescanner.databinding.FragmentBarcodesListBinding;
import com.barcodescanner.models.CreateQrBarcodeListModel;

public class BarcodesListFragment extends Fragment {
    FragmentBarcodesListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBarcodesListBinding.inflate(inflater);
        binding.twoDBarcodesRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.twoDBarcodesRv.setAdapter(new CreateQrBarcodeListAdapter(getActivity(), CreateQrBarcodeListModel.get2DBarcodeListItems()));

        binding.oneDBarcodesRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.oneDBarcodesRv.setAdapter(new CreateQrBarcodeListAdapter(getActivity(), CreateQrBarcodeListModel.get1DBarcodeListItems()));
        return binding.getRoot();

    }
}