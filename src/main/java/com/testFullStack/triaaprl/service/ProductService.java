package com.testFullStack.triaaprl.service;

import com.testFullStack.triaaprl.dao.ProductDao;
import com.testFullStack.triaaprl.dto.ProductDto;
import com.testFullStack.triaaprl.entity.Product;
import com.testFullStack.triaaprl.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDao dao;
    private final CategoryService categoryService;

    public void save(ProductDto.Save data) {
        categoryService.findById(data.getCategoryId());
        this.dao.save(data);}

    public List<Product> findAll(){return this.dao.findAll();}

    public Product findById(Integer id){
        return this.dao.findById(id).orElseThrow(() ->
                new IdNotFoundException("Produk dengan id " + id +" tidak ditemukan"));
    }

    public void delete(Integer id) {this.dao.delete(id);}
    public void update(Integer id, ProductDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
