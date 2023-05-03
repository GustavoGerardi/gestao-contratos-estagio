package com.example.demo.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonSerialize
public class ProcessDtoRequest {
    @NotNull
    private final Long studentUserId;

    private final Long companyId;

    @NotNull
    private final Long documentType;
}
