package com.barcodescanner.utils.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.barcodescanner.models.Barcode;
@Database(entities = {Barcode.class}, version = 1, exportSchema = false)
public abstract class BarcodesDatabase extends RoomDatabase {
    private static BarcodesDatabase sInstance;
    public static final String DATABASE_NAME = "Barcodes";

    public abstract BarcodesDao barcodesDao();
    private static final Object LOCK = new Object();
    public static BarcodesDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), BarcodesDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
            }
        }
        return sInstance;
    }
}
