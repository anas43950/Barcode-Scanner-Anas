package com.barcodescanner.models.qrcodes;

import android.graphics.drawable.Drawable;

import com.barcodescanner.interfaces.ModelOperations;

public class App implements ModelOperations {
    private String packageName;
    private String name;
    private Drawable iconDrawable;

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public App(String name, String packageName, Drawable iconDrawable) {
        this.packageName = packageName;
        this.name = name;
        this.iconDrawable = iconDrawable;
    }

    @Override
    public String toSchema() {
        return "market://details?id=" + packageName;
    }
}
