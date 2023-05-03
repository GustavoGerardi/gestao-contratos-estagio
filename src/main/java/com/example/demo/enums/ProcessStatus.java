package com.example.demo.enums;

public enum ProcessStatus {
    OPEN(1L),
    CLOSED(2L),
    OVERDUE(3L),
    FINISHED(4L);

    private final Long id;

    ProcessStatus(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return this.id;
    }
}
