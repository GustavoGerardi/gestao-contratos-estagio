package com.example.demo.controllers;

import com.example.demo.dto.request.UserDataAccount;
import com.example.demo.entities.AccountEntity;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student")
    public ResponseEntity<AccountEntity> createStudent(@RequestBody UserDataAccount userDataAccount) {
        AccountEntity account = studentService.createStudent(userDataAccount);
        return ResponseEntity.ok(account);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<AccountEntity> getStudentById(@PathVariable Long id) {
        AccountEntity student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity<List<AccountEntity>> getAllStudents() {
        List<AccountEntity> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}
