package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Email implements ModelOperations {
    private String email;
    private String subject;
    private String message;

    public Email(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public String toSchema() {
        return "MATMSG:TO:" + email + ";\n" +
                "SUB:" + subject + ";\n" +
                "BODY:" + message + ";;";
    }
}
