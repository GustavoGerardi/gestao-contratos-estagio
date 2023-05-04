package com.example.demo.services;

import com.example.demo.dto.request.DocumentDtoRequest;
import com.example.demo.enums.ProcessStatus;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentUploadValidation {

    @Autowired
    ProcessService processService;

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @SneakyThrows
    public Boolean validateUpload(DocumentDtoRequest documentDtoRequest, String userType, Long processStatus) {

        if (processStatus.equals(ProcessStatus.ABANDONED.getValue())) {
            throw new Exception("Não é mais possível fazer upload porque este processo foi abandonado.");
        }

        if (userType.equals("STUDENT")) {
            return validateUploadByStudent(processStatus, documentDtoRequest);
        }

        if (userType.equals("ADMIN")) {
            return validateUploadByAdmin(processStatus, documentDtoRequest);
        }

        return false;
    }

    @SneakyThrows
    private Boolean validateUploadByStudent(Long processStatus, DocumentDtoRequest documentDtoRequest) {
        if (processStatus.equals(ProcessStatus.WAITING_FOR_STUDENT.getValue()) ||
                processStatus.equals(ProcessStatus.WAITING_FOR_FATEC.getValue())
        ) {
            processService.changeStatus(ProcessStatus.WAITING_FOR_FATEC, documentDtoRequest.getProcessId());
            return true;
        }

        if (processStatus.equals(ProcessStatus.IN_ANALYSIS.getValue()) ||
                processStatus.equals(ProcessStatus.SENT.getValue()) ||
                processStatus.equals(ProcessStatus.FINISHED.getValue())
        ) {
            throw new Exception("Não é mais possível fazer upload do documento.");
        }
        return false;
    }

    private Boolean validateUploadByAdmin(Long processStatus, DocumentDtoRequest documentDtoRequest) {
        if (adminService.isAdminUser(documentDtoRequest.getUserId()) && processStatus
                .equals(ProcessStatus.IN_ANALYSIS.getValue())) {
            processService.changeStatus(ProcessStatus.SENT, documentDtoRequest.getProcessId());
            return true;
        }
        return false;
    }
}
