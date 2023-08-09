package com.testFullStack.triaaprl.service;

import com.testFullStack.triaaprl.dao.CategoryDao;
import com.testFullStack.triaaprl.dto.CategoryDto;
import com.testFullStack.triaaprl.entity.Category;
import com.testFullStack.triaaprl.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryDao dao;

    public void save(CategoryDto.Save data) {this.dao.save(data);}

    public List<Category> findAll(){return this.dao.findAll();}

    public Category findById(Integer id){
        return this.dao.findByid(id).orElseThrow(() ->
                new IdNotFoundException("Categori dengan id" + id +"tidak ditemukan"));
    }

    public void delete(Integer id) {this.dao.delete(id);}

    public void update(Integer id, CategoryDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
