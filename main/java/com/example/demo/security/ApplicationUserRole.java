package com.example.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.security.ApplicationUserPersmission.*;

public enum ApplicationUserRole {
    NHANVIEN(Sets.newHashSet()),
    QUANLY(Sets.newHashSet(COURCE_READ,COURCE_WRITE,NHANVIEN_READ,NHANVIEN_WRITE));

    private final Set<ApplicationUserPersmission> persmissions;

    ApplicationUserRole(Set<ApplicationUserPersmission> persmissions) {
        this.persmissions = persmissions;
    }

    public Set<ApplicationUserPersmission> getPersmissions() {
        return persmissions;
    }
}
