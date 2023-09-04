package com.barcodescanner.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

import com.barcodescanner.R;
import com.barcodescanner.databinding.ActivityCreateBarcodeBinding;
import com.barcodescanner.enums.CodeType;
import com.barcodescanner.fragments.barcodes.AztecFragment;
import com.barcodescanner.fragments.barcodes.CodabarFragment;
import com.barcodescanner.fragments.barcodes.Code128Fragment;
import com.barcodescanner.fragments.barcodes.Code39Fragment;
import com.barcodescanner.fragments.barcodes.Code93Fragment;
import com.barcodescanner.fragments.barcodes.DataMatrixFragment;
import com.barcodescanner.fragments.barcodes.Ean13Fragment;
import com.barcodescanner.fragments.barcodes.Ean8Fragment;
import com.barcodescanner.fragments.barcodes.ItfFragment;
import com.barcodescanner.fragments.barcodes.Pdf417Fragment;
import com.barcodescanner.fragments.barcodes.UpcAFragment;
import com.barcodescanner.fragments.barcodes.UpcEFragment;
import com.barcodescanner.fragments.qrcodes.AppsFragment;
import com.barcodescanner.fragments.qrcodes.BookmarkFragment;
import com.barcodescanner.fragments.qrcodes.ContactVCardFragment;
import com.barcodescanner.fragments.qrcodes.EmailFragment;
import com.barcodescanner.fragments.qrcodes.EventFragment;
import com.barcodescanner.fragments.qrcodes.LocationFragment;
import com.barcodescanner.fragments.qrcodes.PhoneFragment;
import com.barcodescanner.fragments.qrcodes.SmsFragment;
import com.barcodescanner.fragments.qrcodes.TextFragment;
import com.barcodescanner.fragments.qrcodes.UrlFragment;
import com.barcodescanner.fragments.qrcodes.WifiFragment;
import com.barcodescanner.models.Barcode;
import com.barcodescanner.utils.AdUtils;
import com.barcodescanner.utils.Constants;

public class CreateBarcodeActivity extends BaseActivity {
    ActivityCreateBarcodeBinding binding;
    CodeType codeType;
    public MenuItem doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBarcodeBinding.inflate(getLayoutInflater());
        if (!getIntent().hasExtra(Constants.BARCODE_TYPE)) {
            finish();
//            setContentView(binding.getRoot(), this, "Test", true);
//            loadFragment(new EventFragment());
        } else {
            codeType = (CodeType) getIntent().getSerializableExtra(Constants.BARCODE_TYPE);
            setContentView(binding.getRoot(), this, codeType.getTitle(), true);
            loadRequiredFragment();
        }
    }

    private void loadRequiredFragment() {
        Fragment fragment = new Fragment();
        if (codeType.equals(CodeType.Text)) {
            fragment = new TextFragment();
        } else if (codeType.equals(CodeType.Url)) {
            fragment = new UrlFragment();
        } else if (codeType.equals(CodeType.Wifi)) {
            fragment = new WifiFragment();
        } else if (codeType.equals(CodeType.Location)) {
            fragment = new LocationFragment();
        } else if (codeType.equals(CodeType.ContactVCard)) {
            fragment = new ContactVCardFragment();
        } else if (codeType.equals(CodeType.Event)) {
            fragment = new EventFragment();
        } else if (codeType.equals(CodeType.Phone)) {
            fragment = new PhoneFragment();
        } else if (codeType.equals(CodeType.Email)) {
            fragment = new EmailFragment();
        } else if (codeType.equals(CodeType.Sms)) {
            fragment = new SmsFragment();
        } else if (codeType.equals(CodeType.Bookmark)) {
            fragment = new BookmarkFragment();
        } else if (codeType.equals(CodeType.Apps)) {
            fragment = new AppsFragment();
        } else if (codeType.equals(CodeType.Data_matrix)) {
            fragment = new DataMatrixFragment();
        } else if (codeType.equals(CodeType.Aztec)) {
            fragment = new AztecFragment();
        } else if (codeType.equals(CodeType.Pdf417)) {
            fragment = new Pdf417Fragment();
        } else if (codeType.equals(CodeType.Ean13)) {
            fragment = new Ean13Fragment();
        } else if (codeType.equals(CodeType.Ean8)) {
            fragment = new Ean8Fragment();
        } else if (codeType.equals(CodeType.UpcE)) {
            fragment = new UpcEFragment();
        } else if (codeType.equals(CodeType.UpcA)) {
            fragment = new UpcAFragment();
        } else if (codeType.equals(CodeType.Code128)) {
            fragment = new Code128Fragment();
        } else if (codeType.equals(CodeType.Code93)) {
            fragment = new Code93Fragment();
        } else if (codeType.equals(CodeType.Code39)) {
            fragment = new Code39Fragment();
        } else if (codeType.equals(CodeType.Codabar)) {
            fragment = new CodabarFragment();
        } else if (codeType.equals(CodeType.ITF)) {
            fragment = new ItfFragment();
        }
        loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done_menu, menu);
        return true;
    }


    public void createBarcode(String content) {
        Barcode barcode = new Barcode(System.currentTimeMillis(), content, codeType.getBarcodeFormat(), false);
        Intent previewActivityIntent = new Intent(this, PreviewActivity.class)
                .putExtra(Constants.BARCODE, barcode);
        AdUtils.showRewardedAd(this, previewActivityIntent);
    }
}