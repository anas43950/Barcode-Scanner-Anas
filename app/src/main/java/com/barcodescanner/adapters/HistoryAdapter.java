package com.barcodescanner.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barcodescanner.activities.PreviewActivity;
import com.barcodescanner.databinding.ItemHistoryBinding;
import com.barcodescanner.models.Barcode;
import com.barcodescanner.utils.Constants;
import com.barcodescanner.utils.Utility;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    Context context;
    List<Barcode> barcodes;

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Barcode barcode = barcodes.get(position);
        holder.binding.textContentTv.setText(barcode.getTextContent());
        holder.binding.dateAndTimeTv.setText(Utility.getFormattedDate(barcode.getTimestamp(), "HH:mm:ss     dd-MMM-yyyy"));
        holder.itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, PreviewActivity.class).putExtra(Constants.BARCODE, barcode));
        });
    }

    public HistoryAdapter(Context context, List<Barcode> barcodes) {
        this.context = context;
        this.barcodes = barcodes;
    }

    @Override
    public int getItemCount() {
        return barcodes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemHistoryBinding binding;

        public ViewHolder(@NonNull ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}