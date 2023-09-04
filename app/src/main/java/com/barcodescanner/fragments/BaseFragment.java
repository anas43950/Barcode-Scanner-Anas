package com.barcodescanner.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.fragment.app.Fragment;

import com.barcodescanner.activities.CreateBarcodeActivity;

import java.util.regex.Pattern;

public class BaseFragment extends Fragment {
    protected CreateBarcodeActivity createBarcodeActivity;
    ActivityResultLauncher<Intent> openContactPickerLauncher;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createBarcodeActivity = (CreateBarcodeActivity) getActivity();
        setHasOptionsMenu(true);

        openContactPickerLauncher = registerForActivityResult(new StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //Get the URI and query the content provider for the phone number
                if (result.getData() == null) return;
                onContactSelected(result.getData().getData());
            }
        });
    }

    protected final void openContactPicker(Intent intent) {
        openContactPickerLauncher.launch(intent);
    }
    protected void onContactSelected(Uri uri) {

    }

    protected void applyTextWithoutSpecialCharactersFilter(EditText edittext){
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Check if the text contains any special characters using regex
                String inputText = editable.toString();
                String regex = "^[a-zA-Z0-9\\s]*$"; // Allow letters, digits, and spaces only
                if (!Pattern.matches(regex, inputText)) {
                    // If the text contains special characters, remove them
                    String filteredText = inputText.replaceAll("[^a-zA-Z0-9\\s]", "");
                    edittext.setText(filteredText);
                    edittext.setSelection(filteredText.length());
                }

            }
        });
    }
}