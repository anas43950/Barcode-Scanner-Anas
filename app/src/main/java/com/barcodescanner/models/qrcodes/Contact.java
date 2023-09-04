package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Contact implements ModelOperations {
    private String name;
    private String organization;
    private String title;
    private String email;
    private String phone;
    private String website;

    public Contact(String name, String organization, String title, String email, String phone, String website) {
        this.name = name;
        this.organization = organization;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public String toSchema() {
        return "BEGIN:VCARD\n" +
                "VERSION:4.0\n" +
                "N:" + name + ";;;\n" +
                "ORG:" + organization + "\n" +
                "TITLE:" + title + "\n" +
                "EMAIL:" + email + "\n" +
                "TEL:" + phone + "\n" +
                "URL:" + website + "\n" +
                "END:VCARD"
                ;
    }
}
