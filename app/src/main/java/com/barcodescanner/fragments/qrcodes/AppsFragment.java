package com.barcodescanner.fragments.qrcodes;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.barcodescanner.adapters.AppsAdapter;
import com.barcodescanner.adapters.AppsAdapter.OnAppSelectedListener;
import com.barcodescanner.databinding.FragmentAppsBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.App;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AppsFragment extends BaseFragment implements OnAppSelectedListener {

    FragmentAppsBinding binding;

    public AppsFragment() {
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
        binding = FragmentAppsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadAllApps();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.getItem(0).setVisible(false);
    }


    private void loadAllApps() {
        List<App> apps = new ArrayList<>();
        final PackageManager pm = createBarcodeActivity.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 && (packageInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 0) {
                App app = new App(packageInfo.loadLabel(pm).toString(), packageInfo.packageName, packageInfo.loadIcon(pm));
                apps.add(app);
            }
        }
        apps.sort(new Comparator<App>() {
            @Override
            public int compare(App app1, App app2) {
                return app1.getName().compareTo(app2.getName());
            }
        });
        AppsAdapter appsAdapter = new AppsAdapter(createBarcodeActivity, apps, this);
        binding.appsRv.setLayoutManager(new LinearLayoutManager(createBarcodeActivity));
        binding.appsRv.setAdapter(appsAdapter);
    }

    @Override
    public void onAppSelected(App app) {
        createBarcodeActivity.createBarcode(app.toSchema());
    }
}