package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Bookmark implements ModelOperations {
    private String title;
    private String url;

    public Bookmark(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String toSchema() {
        return "MEBKM:TITLE:" + title + ";" +
                "URL:" + url + ";;";
    }
}
