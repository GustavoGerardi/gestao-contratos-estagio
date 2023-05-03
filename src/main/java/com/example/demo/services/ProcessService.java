package com.example.demo.services;

import com.example.demo.dto.request.ProcessDtoRequest;
import com.example.demo.dto.response.ProcessResponseDto;
import com.example.demo.entities.ProcessEntity;
import com.example.demo.enums.ProcessStatus;
import com.example.demo.repositories.CompanyRepository;
import com.example.demo.repositories.ProcessRepository;
import com.example.demo.repositories.StudentUserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ProcessService {

    @Autowired
    StudentUserRepository studentUserRepository;

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    CompanyRepository companyRepository;


    public ProcessResponseDto createProcess(ProcessDtoRequest processDtoRequest) {
        ProcessEntity processEntity = ProcessEntity.builder()
                .studentUserId(studentUserRepository
                        .getReferenceById(processDtoRequest.getStudentUserId())
                )
                .companyId(Objects.nonNull(processDtoRequest.getCompanyId()) ?
                        processDtoRequest.getCompanyId() : null
                )
                .documentType(processDtoRequest.getDocumentType())
                .processStatus(ProcessStatus.WAITING_FOR_STUDENT.getValue())
                .localDate(LocalDate.now())
                .build();

        ProcessEntity processSaved = processRepository.save(processEntity);

        return ProcessResponseDto.builder()
                .processId(processSaved.getId())
                .userId(processDtoRequest.getStudentUserId())
                .processStatus(ProcessStatus.WAITING_FOR_STUDENT)
                .company(
                        Objects.nonNull(processDtoRequest.getCompanyId()) ?
                                companyRepository
                                        .findById(processDtoRequest
                                                .getCompanyId())
                                        .get()
                                        .getBusinessName() : null
                )
                .build();

    }

    @SneakyThrows
    public Long getProcessStatus(Long id) {
        ProcessEntity process = processRepository.findById(id)
                .orElseThrow(() -> new Exception("Process não encontrado."));

        return process.getProcessStatus();
    }

    public void changeStatus(ProcessStatus processStatus) {
        processRepository.save(ProcessEntity.builder()
                .processStatus(processStatus.getValue())
                .build());
    }
}
