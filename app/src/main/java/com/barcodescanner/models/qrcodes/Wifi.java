package com.barcodescanner.models.qrcodes;

import com.barcodescanner.interfaces.ModelOperations;

public class Wifi implements ModelOperations {
    private String encryptionType;
    private String networkName;
    private String password;

    public Wifi(String networkName, String encryptionType, String password) {
        this.encryptionType = encryptionType;
        this.networkName = networkName;
        this.password = password;
    }


    @Override
    public String toSchema() {
        return "WIFI:" +
                (encryptionType.equals("None") ? "T:nopass" : "T:" + encryptionType) +
                "S:" + networkName +
                (encryptionType.equals("None") ? "" : "P:" + password);
    }
}
