package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Phone implements ModelOperations {
    private String phoneNumber;

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toSchema() {
        return "tel:" + phoneNumber;
    }
}
