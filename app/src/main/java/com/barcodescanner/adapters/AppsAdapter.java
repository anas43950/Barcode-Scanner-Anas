package com.barcodescanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barcodescanner.databinding.ItemAppsRvBinding;
import com.barcodescanner.models.qrcodes.App;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {
    Context context;
    List<App> apps;
    OnAppSelectedListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAppsRvBinding binding = ItemAppsRvBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    public AppsAdapter(Context context, List<App> apps, OnAppSelectedListener listener) {
        this.context = context;
        this.apps = apps;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        App app = apps.get(position);
        holder.binding.iconView.setImageDrawable(app.getIconDrawable());
        holder.binding.nameTv.setText(app.getName());
        holder.binding.packageNameTv.setText(app.getPackageName());
        holder.itemView.setOnClickListener(view -> listener.onAppSelected(app));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemAppsRvBinding binding;

        public ViewHolder(@NonNull ItemAppsRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnAppSelectedListener {
        void onAppSelected(App app);
    }
}