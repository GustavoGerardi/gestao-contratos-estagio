package com.example.demo.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonSerialize
public class DocumentResponseDto {
    private Long documentId;
}
