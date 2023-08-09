package com.testFullStack.triaaprl.controller;

import com.testFullStack.triaaprl.dto.UsersDto;
import com.testFullStack.triaaprl.entity.Users;
import com.testFullStack.triaaprl.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<List<Users>> findAll(){
        List<Users> users = this.service.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(
            @PathVariable(name = "id") Integer id
    ){
        Users user = this.service.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(
            @RequestBody @Valid UsersDto.Save data,
            BindingResult result
    ){
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            for (FieldError fieldError :  result.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            output.put("status", errors);
            return ResponseEntity.badRequest().body(output);
        }
        this.service.save(data);
        output.put("status", "Berhasil menambah user");
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable Integer id
    ){
        this.service.delete(id);
        Map<String, Object> output = new HashMap<>();
        output.put("status", "Data berhasil dihapus");
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Integer id,
            @RequestBody @Valid UsersDto.Save data,
            BindingResult result
    ){
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            for (FieldError fieldError :  result.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            output.put("status", errors);
            return ResponseEntity.badRequest().body(output);
        }
        this.service.update(id, data);
        output.put("status", "Data berhasil diupdate");
        return ResponseEntity.ok(output);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UsersDto.Login inputData) {
        Users user = service.login(inputData);
        if (user != null) {
            // Login successful
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Login successful!\", \"user\": " + "{\"id\": " + user.getId() + ", \"name\": \"" + user.getUsername() + "\", \"email\": \"" + user.getEmail() + "\"}}");
        } else {
            // Login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"success\": false, \"message\": \"Login failed. Invalid credentials.\"}");
        }
    }
}
