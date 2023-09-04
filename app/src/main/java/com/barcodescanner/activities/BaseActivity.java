package com.barcodescanner.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.barcodescanner.R;

public class BaseActivity extends AppCompatActivity {
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        ((TextView) findViewById(R.id.title_tv)).setText(title);
    }

    public void setContentView(View view, BaseActivity activity, String title, boolean showBackBtn) {
        super.setContentView(view);
        activity.setSupportActionBar(activity.findViewById(R.id.toolbar));
        super.setTitle("");
        if (title != null && !title.equals("")) {
            setTitle(title);
        }
        if (showBackBtn) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment).commit();
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void copyToClipboard(String text){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", text);
        clipboard.setPrimaryClip(clip);
        showToast("Copied");
    }
}