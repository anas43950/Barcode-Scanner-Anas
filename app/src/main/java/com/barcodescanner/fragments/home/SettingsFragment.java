package com.barcodescanner.fragments.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceFragmentCompat;

import com.barcodescanner.R;
import com.barcodescanner.utils.AdUtils;
import com.barcodescanner.utils.database.BarcodesDatabase;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdUtils.showInterstitialAd(getActivity());
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getActivity().setTitle("Settings");
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.preferences);

    }

    @Override
    public boolean onPreferenceTreeClick(@NonNull Preference preference) {
        if (preference.getKey().equals(getString(R.string.clear_history_key))) {
            BarcodesDatabase database = BarcodesDatabase.getInstance(getContext());
            database.barcodesDao().deleteAllHistory();
            Toast.makeText(getContext(), "Deleted all history items", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }
}