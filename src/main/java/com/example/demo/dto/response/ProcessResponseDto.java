package com.example.demo.dto.response;

import com.example.demo.enums.DocumentType;
import com.example.demo.enums.ProcessStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@JsonSerialize
public class ProcessResponseDto {
    private final Long processId;
    private final Long userId;
    private final String company;
    private final DocumentType documentType;
    private final ProcessStatus processStatus;
    private final LocalDate localDate;
}
