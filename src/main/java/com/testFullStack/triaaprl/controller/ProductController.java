package com.testFullStack.triaaprl.controller;

import com.testFullStack.triaaprl.dto.ProductDto;
import com.testFullStack.triaaprl.entity.Product;
import com.testFullStack.triaaprl.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = this.service.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(
            @PathVariable(name = "id") Integer id
    ){
        Product product = this.service.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> save(
            @RequestBody @Valid ProductDto.Save data,
            BindingResult result
    ){
        System.out.println(data);
        Map<String, Object> output = new HashMap<>();
        if (result.hasErrors()){
            Map<String, Object> errors = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()){
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());

            }
            output.put("status", errors);
            return ResponseEntity.badRequest().body(output);
        }
        this.service.save(data);
        output.put("status", "Berhasil menambah Product");
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable Integer id
    ){
        this.service.delete(id);
        Map<String, Object> output = new HashMap<>();
        output.put("status","data berhasil dihapus");
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(
            @PathVariable Integer id,
            @RequestBody @Valid ProductDto.Save data,
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
}
