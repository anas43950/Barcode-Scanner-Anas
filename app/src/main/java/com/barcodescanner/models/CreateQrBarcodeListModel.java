package com.barcodescanner.models;

import com.barcodescanner.R;
import com.barcodescanner.enums.CodeType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateQrBarcodeListModel implements Serializable {

    private String text;
    private int iconReference;
    private CodeType codeType;

    public CreateQrBarcodeListModel(String text, int iconReference, CodeType codeType) {
        this.text = text;
        this.iconReference = iconReference;
        this.codeType = codeType;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIconReference(int iconReference) {
        this.iconReference = iconReference;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public String getText() {
        return text;
    }

    public int getIconReference() {
        return iconReference;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public static List<CreateQrBarcodeListModel> getQrListItems() {
        List<CreateQrBarcodeListModel> list = new ArrayList<>();
        list.add(new CreateQrBarcodeListModel("Text", R.drawable.ic_edit, CodeType.Text));
        list.add(new CreateQrBarcodeListModel("URL", R.drawable.ic_link, CodeType.Url));
        list.add(new CreateQrBarcodeListModel("Wi-fi", R.drawable.ic_wifi, CodeType.Wifi));
        list.add(new CreateQrBarcodeListModel("Location", R.drawable.ic_location, CodeType.Location));
        list.add(new CreateQrBarcodeListModel("Contact (VCard)", R.drawable.ic_contact, CodeType.ContactVCard));
        list.add(new CreateQrBarcodeListModel("Event", R.drawable.ic_event, CodeType.Event));
        list.add(new CreateQrBarcodeListModel("Phone", R.drawable.ic_phone, CodeType.Phone));
        list.add(new CreateQrBarcodeListModel("Email", R.drawable.ic_email, CodeType.Email));
        list.add(new CreateQrBarcodeListModel("SMS", R.drawable.ic_sms, CodeType.Sms));
        list.add(new CreateQrBarcodeListModel("Bookmark", R.drawable.ic_bookmark, CodeType.Bookmark));
        list.add(new CreateQrBarcodeListModel("Apps", R.drawable.ic_apps, CodeType.Apps));
        return list;
    }

    public static List<CreateQrBarcodeListModel> get2DBarcodeListItems() {
        List<CreateQrBarcodeListModel> list = new ArrayList<>();
        list.add(new CreateQrBarcodeListModel("Data Matrix", R.drawable.ic_data_matrix, CodeType.Data_matrix));
        list.add(new CreateQrBarcodeListModel("Aztec", R.drawable.ic_aztec, CodeType.Aztec));
        list.add(new CreateQrBarcodeListModel("PDF417", R.drawable.ic_barcode, CodeType.Pdf417));
        return list;
    }

    public static List<CreateQrBarcodeListModel> get1DBarcodeListItems() {
        List<CreateQrBarcodeListModel> list = new ArrayList<>();
        list.add(new CreateQrBarcodeListModel("EAN-13", R.drawable.ic_barcode, CodeType.Ean13));
        list.add(new CreateQrBarcodeListModel("EAN-8", R.drawable.ic_barcode, CodeType.Ean8));
        list.add(new CreateQrBarcodeListModel("UPC-E", R.drawable.ic_barcode, CodeType.UpcE));
        list.add(new CreateQrBarcodeListModel("UPC-A", R.drawable.ic_barcode, CodeType.UpcA));
        list.add(new CreateQrBarcodeListModel("Code 128", R.drawable.ic_barcode, CodeType.Code128));
        list.add(new CreateQrBarcodeListModel("Code 93", R.drawable.ic_barcode, CodeType.Code93));
        list.add(new CreateQrBarcodeListModel("Code 39", R.drawable.ic_barcode, CodeType.Code39));
        list.add(new CreateQrBarcodeListModel("Codabar", R.drawable.ic_barcode, CodeType.Codabar));
        list.add(new CreateQrBarcodeListModel("ITF", R.drawable.ic_barcode, CodeType.ITF));
        return list;
    }
}
