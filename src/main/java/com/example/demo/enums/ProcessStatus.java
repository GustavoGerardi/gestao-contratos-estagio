package com.example.demo.enums;

public enum ProcessStatus {
    WAITING_FOR_STUDENT(1L),
    WAITING_FOR_SECRETARY(2L),
    IN_ANALYSIS(3L),
    SENT(4L),
    FINISHED(5L),
    OVERDUE(6L),
    ABANDONED(7L);

    private final Long id;

    ProcessStatus(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return this.id;
    }
}
