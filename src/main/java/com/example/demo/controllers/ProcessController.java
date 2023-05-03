package com.example.demo.controllers;

import com.example.demo.dto.request.ProcessDtoRequest;
import com.example.demo.dto.response.ProcessResponseDto;
import com.example.demo.services.ProcessService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    ProcessService processService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<ProcessResponseDto> createProcess(@RequestBody ProcessDtoRequest processDtoRequest) {
        return ResponseEntity.ok(processService.createProcess(processDtoRequest));
    }
}
