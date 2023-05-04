package com.example.demo.enums;

public enum DocumentStatus {
    TO_SIGN(0),
    READY(1);

    private final int id;

    DocumentStatus(int id) {
        this.id = id;
    }

    public int getValue() {
        return this.id;
    }
}
