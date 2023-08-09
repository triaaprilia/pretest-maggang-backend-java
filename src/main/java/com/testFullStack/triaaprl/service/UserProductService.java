package com.testFullStack.triaaprl.service;

import com.testFullStack.triaaprl.dao.UserProductDao;
import com.testFullStack.triaaprl.dto.UserProductDto;
import com.testFullStack.triaaprl.entity.Product;
import com.testFullStack.triaaprl.entity.UserProduct;
import com.testFullStack.triaaprl.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProductService {
    private final UserProductDao dao;
    private final UserService userService;
    private final ProductService productService;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(UserProductDto.Save data) {
        userService.findById(data.getUserId());
        productService.findById(data.getProductId());
        Product product = productService.findById(data.getProductId());
        int quantity = data.getQuantity();
        if (product.getStock() >= quantity) {
            int SisaStock = product.getStock() - quantity;
            String query = """
                    UPDATE user_product SET
                    quantity=:quantity
                    WHERE id=:id
                    """;
            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("quantity", SisaStock);
            map.addValue("id", product.getId());

            this.jdbcTemplate.update(query, map);
            this.dao.save(data);
        } else {
            throw new IllegalArgumentException("Stock kosong");
        }
    }


    public List<UserProduct> findAll() {
        return this.dao.findAll();
    }

    public UserProduct findById(Integer id) {
        return this.dao.findById(id).orElseThrow(() ->
                new IdNotFoundException("UserProduct dengan id " + id + " tidak ditemukan"));
    }

    public void delete(Integer id) {
        this.dao.delete(id);
    }

    public void update(Integer id, UserProductDto.Save data) {
        findById(id);
        this.dao.update(id, data);
    }
}
