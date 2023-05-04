package com.example.demo.services;

import com.example.demo.dto.request.DocumentDtoRequest;
import com.example.demo.dto.response.DocumentResponseDto;
import com.example.demo.entities.Document;
import com.example.demo.entities.ProcessEntity;
import com.example.demo.enums.DocumentStatus;
import com.example.demo.enums.ProcessStatus;
import com.example.demo.repositories.DocumentRepository;
import com.example.demo.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DocumentUploadValidation documentUploadValidation;


    @Autowired
    StudentService studentService;


    @Autowired
    AdminService adminService;


    @Autowired
    ProcessService processService;

    @Value("${bucket}")
    private String UPLOAD_DIR;

    @SneakyThrows
    public DocumentResponseDto uploadDocument(MultipartFile document, DocumentDtoRequest documentDtoRequest) {

        String userType = "";

        if (adminService.isAdminUser(documentDtoRequest.getUserId())) {
            userType = "ADMIN";
        } else if (studentService.isStudentUser(documentDtoRequest.getUserId())) {
            userType = "STUDENT";
        }


        if (documentUploadValidation.validateUpload(documentDtoRequest, userType)) {
            String url = UPLOAD_DIR + document.getOriginalFilename();
            Path dest = Paths.get(url);
            Files.write(dest, document.getBytes());

            Document documentToSave;

            Long existingDocumentId = documentExistsAlready(documentDtoRequest);

            if (Objects.nonNull(existingDocumentId)) {
                documentToSave = Document.builder()
                        .id(existingDocumentId)
                        .process(ProcessEntity.builder().id(documentDtoRequest.getProcessId()).build())
                        .postedBy(userRepository.getReferenceById(documentDtoRequest.getUserId()))
                        .postDate(LocalDate.now())
                        .url(url)
                        .build();
            } else {
                documentToSave = Document.builder()
                        .process(ProcessEntity.builder().id(documentDtoRequest.getProcessId()).build())
                        .postedBy(userRepository.getReferenceById(documentDtoRequest.getUserId()))
                        .postDate(LocalDate.now())
                        .url(url)
                        .build();
            }

            Document documentSaved = documentRepository.save(documentToSave);

            return DocumentResponseDto.builder()
                    .documentId(documentSaved.getId())
                    .build();
        }

        throw new Exception("Upload não pôde ser realizado");

    }

    @SneakyThrows
    public ByteArrayResource downloadDocument(Long processId, DocumentStatus documentStatus) {
        List<Document> docList = documentRepository
                .findByProcess(ProcessEntity.builder().id(processId).build());

        if (docList.isEmpty()) {
            throw new Exception("Nenhum documento está disponível para download no momento.");
        }

        if (documentStatus.equals(DocumentStatus.READY)) {
            processService.changeStatus(ProcessStatus.FINISHED, processId);
        }

        Path path = Paths.get(docList.get(documentStatus.getValue()).getUrl());

        byte[] pdf = Files.readAllBytes(path);

        return new ByteArrayResource(pdf);
    }

    @SneakyThrows
    private Long documentExistsAlready(DocumentDtoRequest documentDtoRequest) {
        try {
            return documentRepository.findByProcessIdAndUserId(documentDtoRequest.getProcessId(),
                    documentDtoRequest.getUserId()).getId();
        } catch (Exception e) {
            return null;
        }
    }
}
