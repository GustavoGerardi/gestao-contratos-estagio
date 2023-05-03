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
    public Boolean validateUpload(DocumentDtoRequest documentDtoRequest) {

        Long processStatus = processService.getProcessStatus(documentDtoRequest.getProcessId());

        //se processado estiver ABANDONED -> levantar erro
        if (processStatus.equals(ProcessStatus.ABANDONED.getValue())) {
            throw new Exception("Não é mais possível fazer upload porque este processo foi abandonado.");
        }

        //se user for student
        //se status for WAITING_FOR_STUDENT(sem upload ainda) ou WAITING_FOR_SECRETARY (já com o upload)
        // entao eu posso aceitar um upload aqui.
        Boolean studentExists = studentService.studentUserExist(documentDtoRequest.getUserId());

        if (studentExists && (processStatus.equals(ProcessStatus.WAITING_FOR_STUDENT.getValue()) ||
                documentDtoRequest.getProcessId().equals(ProcessStatus.WAITING_FOR_SECRETARY.getValue()))
        ) {
            return true;
        }

        // se status for IN_ANALYSIS, SENT, FINISHED
        // então levantar exceção (Não é mais possível fazer o upload do documento)
        if (studentExists && (processStatus.equals(ProcessStatus.IN_ANALYSIS.getValue()) ||
                processStatus.equals(ProcessStatus.SENT.getValue()) ||
                processStatus.equals(ProcessStatus.FINISHED.getValue())
        )) {
            throw new Exception("Não é mais possível fazer upload do documento.");
        }

        //se user from admin
        // se status for IN_ANALYSIS
        // // entao eu posso aceitar um upload aqui e mudar status para SENT
        if (adminService.adminUserExist(documentDtoRequest.getUserId()) && processStatus
                .equals(ProcessStatus.IN_ANALYSIS.getValue())) {
            return true;
        }

        throw new Exception("Nenhum status foi encontrado para este processo.");
    }
}
