package com.barcodescanner.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.barcodescanner.R;
import com.barcodescanner.activities.BaseActivity;
import com.barcodescanner.activities.MyApplication;
import com.barcodescanner.databinding.FragmentQrBarcodeListBinding;
import com.barcodescanner.fragments.home.create.BarcodesListFragment;
import com.barcodescanner.fragments.home.create.QrListFragment;
import com.barcodescanner.utils.AdUtils;
import com.google.android.material.tabs.TabLayout.Tab;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy;

public class QrBarcodeListFragment extends Fragment {
    FragmentQrBarcodeListBinding binding;

    public QrBarcodeListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdUtils.showInterstitialAd(getActivity());
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getActivity().setTitle("Create");


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentQrBarcodeListBinding.inflate(inflater);

        setUpViewPager();
        return binding.getRoot();
    }

    private void setUpViewPager() {
        binding.viewPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment = new Fragment();
                if (position == 0) {
                    fragment = new QrListFragment();
                } else if (position == 1) {
                    fragment = new BarcodesListFragment();
                }
                return fragment;
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        });
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull Tab tab, int position) {
                if (position == 0) {
                    tab.setText("QR Code");
                    tab.setIcon(R.drawable.ic_qr_code);
                } else if (position == 1) {
                    tab.setText("Barcode");
                    tab.setIcon(R.drawable.ic_barcode);
                }
            }
        }).attach();
        binding.viewPager.setCurrentItem(0, false);


    }

}