package com.example.demo.controllers;

import com.example.demo.dto.request.DocumentDtoRequest;
import com.example.demo.dto.response.DocumentResponseDto;
import com.example.demo.services.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    public DocumentService documentService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @SneakyThrows
    public ResponseEntity<DocumentResponseDto> uploadDocument(
            @RequestPart(value = "document") MultipartFile document,
            @RequestPart(value = "documentDtoRequest") String documentDtoRequest
    ) {

        ObjectMapper objectMapper = new ObjectMapper();

        DocumentDtoRequest dto = objectMapper.readValue(documentDtoRequest, DocumentDtoRequest.class);

        if (document.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(documentService.uploadDocument(document, dto));
    }

//    @GetMapping
//    @SneakyThrows
//    public ResponseEntity<DocumentResponseDto> downloadDocument(@RequestBody DocumentDtoRequest documentDtoRequest
//    ) {
//        return ResponseEntity.ok(documentService.downloadDocument(documentDtoRequest));
//    }

}
