package com.example.demo.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonSerialize
@NoArgsConstructor
public class DocumentDtoRequest {
    private Long userId;
    private Long processId;
}
