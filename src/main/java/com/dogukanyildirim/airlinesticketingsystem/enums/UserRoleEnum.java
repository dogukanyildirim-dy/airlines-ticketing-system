package com.dogukanyildirim.airlinesticketingsystem.enums;

/**
 * Kullanıcı rolleri için enumeration nesnesi
 *
 * @author dogukan.yildirim
 */

public enum UserRoleEnum {
    ADMIN("ADMIN"),
    PASSENGER("PASSENGER");

    private final String roleName;

    UserRoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
