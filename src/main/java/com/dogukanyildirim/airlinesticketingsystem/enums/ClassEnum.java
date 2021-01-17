package com.dogukanyildirim.airlinesticketingsystem.enums;

/**
 * Uçuş paketleri için enumeration nesnesi
 *
 * @author dogukan.yildirim
 */

public enum ClassEnum {
    ECONOMY("ECONOMY"),
    BUSINESS("BUSINESS"),
    FIRST("FIRST");

    private final String className;

    ClassEnum(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
