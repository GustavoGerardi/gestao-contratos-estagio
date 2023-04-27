package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum DocumentType {
    CONTRACT(1L),
    REPORT(2L);

    private final Long id;

    DocumentType(Long id) {
        this.id = id;
    }
}
