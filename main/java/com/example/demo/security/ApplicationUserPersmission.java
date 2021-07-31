package com.example.demo.security;

public enum ApplicationUserPersmission {
    NHANVIEN_READ("nhanvien:read"),
    NHANVIEN_WRITE("nhanvien:write"),
    COURCE_READ("cource:read"),
    COURCE_WRITE("cource:write");

    private final String persmission;

    ApplicationUserPersmission(String persmission) {
        this.persmission = persmission;
    }

    public String getPersmission() {
        return persmission;
    }
}
