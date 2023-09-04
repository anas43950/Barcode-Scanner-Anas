package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Sms implements ModelOperations {
    private String phoneNumber;
    private String message;

    public Sms(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    @Override
    public String toSchema() {
        return "smsto:" + phoneNumber + ":" + message;
    }
}
