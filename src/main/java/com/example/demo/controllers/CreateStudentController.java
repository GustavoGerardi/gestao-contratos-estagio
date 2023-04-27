package com.example.demo.controllers;

import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.StudentUserEntity;
import com.example.demo.services.CreateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("account")

public class CreateStudentController {

    @Autowired
    private CreateStudentService createStudentService;

    @PostMapping("/createstudent")
    public ResponseEntity<StudentUserEntity> createStudent(@RequestBody UserDataAccount userDataAccount) {
        createStudentService.createStudent(userDataAccount);
        return null;
    }
}
