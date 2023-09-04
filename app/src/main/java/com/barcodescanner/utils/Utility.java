package com.barcodescanner.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utility {
    public static String getFormattedDate(long timestamp, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(timestamp);
    }
}
