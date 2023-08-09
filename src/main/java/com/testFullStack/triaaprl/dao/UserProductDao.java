package com.testFullStack.triaaprl.dao;

import com.testFullStack.triaaprl.dto.UserProductDto;
import com.testFullStack.triaaprl.entity.UserProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserProductDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save(UserProductDto.Save inputdata) {
        String query = "INSERT INTO public.user_product\n" +
                "(user_id, product_id, quantity)\n" +
                "VALUES(:user_id, :product_id, :quantity);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("user_id", inputdata.getUserId());
        map.addValue("product_id", inputdata.getProductId());
        map.addValue("quantity", inputdata.getQuantity());

        this.jdbcTemplate.update(query, map);
    }

    public List<UserProduct> findAll() {
        String query = "SELECT id, user_id, product_id, quantity\n" +
                "FROM public.user_product;\n";
        return this.jdbcTemplate.query(query, new RowMapper<UserProduct>() {
            @Override
            public UserProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserProduct userProduct = new UserProduct();
                userProduct.setId(rs.getInt("id"));
                userProduct.setUserId(rs.getInt("user_id"));
                userProduct.setProductId(rs.getInt("product_id"));
                userProduct.setQuantity(rs.getInt("quantity"));
                return userProduct;
            }
        });
    }

    public Optional<UserProduct> findById(Integer id) {
        String query = "SELECT id, user_id, product_id, quantity\n" +
                "FROM public.user_product where id=:id\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        try {
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<UserProduct>>() {
                @Override
                public Optional<UserProduct> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    UserProduct userProduct = new UserProduct();
                    userProduct.setId(rs.getInt("id"));
                    userProduct.setUserId(rs.getInt("user_id"));
                    userProduct.setProductId(rs.getInt("product_id"));
                    userProduct.setQuantity(rs.getInt("quantity"));
                    return Optional.of(userProduct);
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void delete(Integer id) {
        String query = "DELETE FROM public.user_product\n" +
                "WHERE id=:id\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id", id);
        this.jdbcTemplate.update(query, map);
    }

    public void deleteByUser(Integer id) {
        String query = """
                DELETE FROM public.user_product WHERE user_id=:id
                 """;

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        this.jdbcTemplate.update(query, map);
    }

    public void update(Integer id, UserProductDto.Save inputData) {
        String query = """
                UPDATE public.user_product
                SET user_id=:user_id, product_id=:product_id, quantity=:quantity
                WHERE id=:id
                """;
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("user_id", inputData.getUserId());
        map.addValue("product_id", inputData.getProductId());
        map.addValue("quantity", inputData.getQuantity());

        this.jdbcTemplate.update(query, map);
    }
}
