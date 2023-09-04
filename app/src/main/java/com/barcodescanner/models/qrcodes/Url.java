package com.barcodescanner.models.qrcodes;

public class Url {
    public static String toSchema(String url) {
        return "URLTO:" + url;
    }

    public static String parse(String data) {
        return data.split(":", 2)[1];
    }
}
