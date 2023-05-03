package com.example.demo.services;

import com.example.demo.dto.request.DocumentDtoRequest;
import com.example.demo.dto.response.DocumentResponseDto;
import com.example.demo.entities.Document;
import com.example.demo.entities.ProcessEntity;
import com.example.demo.repositories.DocumentRepository;
import com.example.demo.repositories.ProcessRepository;
import com.example.demo.repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    ProcessRepository processRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    DocumentUploadValidation documentUploadValidation;

    @Value("${bucket}")
    private String UPLOAD_DIR;

    @SneakyThrows
    public DocumentResponseDto uploadDocument(MultipartFile document, DocumentDtoRequest documentDtoRequest) {
        if (documentUploadValidation.validateUpload(documentDtoRequest)) {
            String url = UPLOAD_DIR + document.getOriginalFilename();
            Path dest = Paths.get(url);
            Files.write(dest, document.getBytes());

            Document documentToSave = Document.builder()
                    .process(ProcessEntity.builder().id(documentDtoRequest.getProcessId()).build())
                    .postedBy(userRepository.getReferenceById(documentDtoRequest.getUserId()))
                    .postDate(LocalDate.now())
                    .url(url)
                    .build();

            Document documentSaved = documentRepository.save(documentToSave);

            return DocumentResponseDto.builder()
                    .documentId(documentSaved.getId())
                    .build();
        }

        throw new Exception("Upload não pôde ser realizado");

    }

//    @SneakyThrows
//    public DocumentResponseDto downloadDocument(DocumentDtoRequest documentDtoRequest) {
//        Document documentSaved = documentRepository.save(documentToSave);
//
//        return DocumentResponseDto.builder()
//                .documentId(documentSaved.getId())
//                .build();
//    }
}
