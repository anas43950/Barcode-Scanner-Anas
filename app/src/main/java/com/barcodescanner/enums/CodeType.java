package com.barcodescanner.enums;

import com.google.zxing.BarcodeFormat;

public enum CodeType {
    Text("Text", BarcodeFormat.QR_CODE),
    Url("URL", BarcodeFormat.QR_CODE),
    Wifi("Wi-fi", BarcodeFormat.QR_CODE),
    Location("Location", BarcodeFormat.QR_CODE),
    ContactVCard("ContactVCard", BarcodeFormat.QR_CODE),
    Event("Event", BarcodeFormat.QR_CODE),
    Phone("Phone", BarcodeFormat.QR_CODE),
    Email("Email", BarcodeFormat.QR_CODE),
    Sms("SMS", BarcodeFormat.QR_CODE),
    Bookmark("Bookmark", BarcodeFormat.QR_CODE),
    Apps("Apps", BarcodeFormat.QR_CODE),
    Data_matrix("Data Matrix", BarcodeFormat.DATA_MATRIX),
    Aztec("Aztec", BarcodeFormat.AZTEC),
    Pdf417("PDF417", BarcodeFormat.PDF_417),
    Ean13("EAN-13", BarcodeFormat.EAN_13),
    Ean8("EAN-8", BarcodeFormat.EAN_8),
    UpcE("UPC-E", BarcodeFormat.UPC_E),
    UpcA("UPC-A", BarcodeFormat.UPC_A),
    Code128("Code 128", BarcodeFormat.CODE_128),
    Code93("Code 93", BarcodeFormat.CODE_93),
    Code39("Code 39", BarcodeFormat.CODE_39),
    Codabar("Codabar", BarcodeFormat.CODABAR),
    ITF("ITF", BarcodeFormat.ITF);
    final String title;
    final BarcodeFormat barcodeFormat;

    CodeType(String title, BarcodeFormat barcodeFormat) {
        this.title = title;
        this.barcodeFormat = barcodeFormat;
    }

    public String getTitle() {
        return title;
    }

    public BarcodeFormat getBarcodeFormat() {
        return barcodeFormat;
    }
}

