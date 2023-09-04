package com.barcodescanner.fragments.qrcodes;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.annotation.NonNull;

import com.barcodescanner.R;
import com.barcodescanner.databinding.FragmentContactVCardBinding;
import com.barcodescanner.fragments.BaseFragment;
import com.barcodescanner.models.qrcodes.Contact;

public class ContactVCardFragment extends BaseFragment {
    FragmentContactVCardBinding binding;

    public ContactVCardFragment() {
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
        binding = FragmentContactVCardBinding.inflate(inflater);
        binding.nameContainer.setEndIconOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                openContactPicker(i);
            }
        });
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done_menu) {
            String firstName = binding.nameEt.getText().toString().trim();
            String organization = binding.organizationEt.getText().toString().trim();
            String job = binding.jobEt.getText().toString().trim();
            String email = binding.emailEt.getText().toString().trim();
            String phone = binding.phoneEt.getText().toString().trim();
            String website = binding.websiteEt.getText().toString().trim();

            createBarcodeActivity.createBarcode(new Contact(firstName, organization, job, email, phone, website).toSchema());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onContactSelected(Uri uri) {
        Contact contact = getContactFromUri(uri);
        loadContactData(contact);
    }

    private Contact getContactFromUri(Uri contactUri) {
        String name = "", organization = "", jobTitle = "", email = "", phone = "", website = "";
        try (Cursor cursor = createBarcodeActivity.getContentResolver().query(contactUri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                Cursor organizationCursor = createBarcodeActivity.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                        null,
                        ContactsContract.Data.CONTACT_ID + " = ? AND " +
                                ContactsContract.Data.MIMETYPE + " = ?",
                        new String[]{contactId, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE},
                        null);

                Cursor emailCursor = createBarcodeActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{contactId},
                        null);
                Cursor phoneCursor = createBarcodeActivity.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{contactId},
                        null);
                Cursor websiteCursor = createBarcodeActivity.getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Website.CONTACT_ID + " = ? AND " +
                                ContactsContract.Data.MIMETYPE + " = ?",
                        new String[]{contactId, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE},
                        null);
                if (organizationCursor != null && organizationCursor.moveToFirst()) {
                    organization = organizationCursor.getString(organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.COMPANY));
                    jobTitle = organizationCursor.getString(organizationCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Organization.TITLE));
                    organizationCursor.close();
                }

                if (emailCursor != null && emailCursor.moveToFirst()) {
                    email = emailCursor.getString(emailCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    emailCursor.close();
                }


                if (phoneCursor != null && phoneCursor.moveToFirst()) {
                    phone = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneCursor.close();
                }


                if (websiteCursor != null && websiteCursor.moveToFirst()) {
                    website = websiteCursor.getString(websiteCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Website.URL));
                    websiteCursor.close();
                }
            }
        }
        return new Contact(name, organization, jobTitle, email, phone, website);
    }


    private void loadContactData(Contact contact) {
        binding.nameEt.setText(contact.getName());
        binding.organizationEt.setText(contact.getOrganization());
        binding.jobEt.setText(contact.getTitle());
        binding.emailEt.setText(contact.getEmail());
        binding.phoneEt.setText(contact.getPhone());
        binding.websiteEt.setText(contact.getWebsite());
    }
}