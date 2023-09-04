package com.barcodescanner.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.barcodescanner.R;
import com.barcodescanner.activities.CreateBarcodeActivity;
import com.barcodescanner.databinding.ItemCreateQrCodeListBinding;
import com.barcodescanner.enums.CodeType;
import com.barcodescanner.models.CreateQrBarcodeListModel;
import com.barcodescanner.utils.Constants;

import java.util.List;

public class CreateQrBarcodeListAdapter extends RecyclerView.Adapter<CreateQrBarcodeListAdapter.ViewHolder> {
    Context context;
    List<CreateQrBarcodeListModel> list;

    public CreateQrBarcodeListAdapter(Context context, List<CreateQrBarcodeListModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CreateQrBarcodeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCreateQrCodeListBinding binding = ItemCreateQrCodeListBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateQrBarcodeListAdapter.ViewHolder holder, int position) {
        CreateQrBarcodeListModel code = list.get(position);
        holder.binding.labelView.setText(code.getText());
        holder.binding.iconView.setImageResource(code.getIconReference());
        if (code.getCodeType().equals(CodeType.Data_matrix) || code.getCodeType().equals(CodeType.Aztec) || code.getCodeType().equals(CodeType.Pdf417)) {
            holder.binding.iconView.setBackground(AppCompatResources.getDrawable(context, R.drawable.circular_shape_orange));
        }
        holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context, CreateBarcodeActivity.class)
                .putExtra(Constants.BARCODE_TYPE, code.getCodeType())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCreateQrCodeListBinding binding;

        public ViewHolder(@NonNull ItemCreateQrCodeListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}