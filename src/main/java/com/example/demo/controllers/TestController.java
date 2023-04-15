package com.example.demo.controllers;

import com.example.demo.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class TestController {

    private final String UPLOAD_DIR = "/home/danilo/";


    @GetMapping()
    public ResponseEntity<User> getUserById() {
        User user = User.builder().build();
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException, IOException {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo não pode ser vazio");
        }

        Path dest = Paths.get(UPLOAD_DIR + file.getOriginalFilename());

        Files.write(dest, file.getBytes());

        return ResponseEntity.ok("Arquivo enviado com sucesso e salvo em " + UPLOAD_DIR);
    }
    

}