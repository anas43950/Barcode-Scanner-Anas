package com.barcodescanner.utils.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.barcodescanner.models.Barcode;

import java.util.List;

@Dao
public interface BarcodesDao {

    @Query("SELECT * FROM barcodes")
    List<Barcode> loadAllHistoryItems();

    @Query("UPDATE barcodes SET isFavorite = :favorite")
    void setFavorite(int favorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addBarcode(Barcode barcode);

    @Delete
    void deleteBarcode(Barcode barcode);

    @Query("DELETE FROM barcodes")
    void deleteAllHistory();
}
