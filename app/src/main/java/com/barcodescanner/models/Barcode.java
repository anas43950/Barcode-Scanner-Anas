package com.barcodescanner.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.zxing.BarcodeFormat;

import java.io.Serializable;

@Entity(tableName = "barcodes")
public class Barcode implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long timestamp;
    private String textContent;
    private BarcodeFormat barcodeFormat;
    private boolean isFavorite;

    public Barcode(int id, long timestamp, String textContent, BarcodeFormat barcodeFormat, boolean isFavorite) {
        this.id = id;
        this.timestamp = timestamp;
        this.textContent = textContent;
        this.barcodeFormat = barcodeFormat;
        this.isFavorite = isFavorite;
    }

    @Ignore
    public Barcode(long timestamp, String textContent, BarcodeFormat barcodeFormat, boolean isFavorite) {
        this.timestamp = timestamp;
        this.textContent = textContent;
        this.barcodeFormat = barcodeFormat;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTextContent() {
        return textContent;
    }

    public BarcodeFormat getBarcodeFormat() {
        return barcodeFormat;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setBarcodeFormat(BarcodeFormat barcodeFormat) {
        this.barcodeFormat = barcodeFormat;
    }
}
