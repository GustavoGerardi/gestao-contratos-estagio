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
import java.util.Optional;

@Service
public class ProcessService {

    @Autowired
    StudentUserRepository studentUserRepository;

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    CompanyRepository companyRepository;


    @Autowired
    AccountService accountService;

    @Autowired
    EmailSenderService emailSenderService;


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
                .orElseThrow(() -> new Exception("Processo não encontrado."));

        return process.getProcessStatus();
    }

    public void changeStatus(ProcessStatus processStatus, Long processId) {

        Optional<ProcessEntity> oldProcessEntity = processRepository.findById(processId);

        oldProcessEntity.ifPresent(processEntity -> processRepository.save(ProcessEntity.builder()
                .id(processId)
                .studentUserId(oldProcessEntity.get().getStudentUserId())
                .localDate(oldProcessEntity.get().getLocalDate())
                .companyId(processEntity.getCompanyId())
                .documentType(processEntity.getDocumentType())
                .processStatus(processStatus.getValue())
                .build()));
    }

    @SneakyThrows
    public String updateProcess(Long processId) {
        emailSenderService.sendUpdateStatusEmail(getStudentEmail(processId),
                "Seu documento foi para ser assinado!",
                "Seu documento está indo para a mesa do diretor para ser assinado"
        );

        changeStatus(ProcessStatus.IN_ANALYSIS, processId);
        return "Sucesso";
    }

    @SneakyThrows
    public String notifyStudentOfWrongDocument(Long processId) {
        emailSenderService.sendEmailOfWrongDocument(getStudentEmail(processId));
        return "Usuário notificado";
    }

    @SneakyThrows
    public String getStudentEmail(Long processId) {
        Optional<ProcessEntity> process = processRepository.findById(processId);

        if (process.isPresent()) {
            return accountService.getStudentAccountById(process.get().getStudentUserId().getId()).getEmail();
        }
        throw new Exception("Email não encontrado");
    }
}
