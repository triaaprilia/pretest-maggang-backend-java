package com.testFullStack.triaaprl.dao;

import com.testFullStack.triaaprl.dto.UsersDto;
import com.testFullStack.triaaprl.entity.Users;
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
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public void save (UsersDto.Save inputdata){
        String query = "INSERT INTO public.user\n" +
                "( \"username\", email, phone)\n" +
                "VALUES(:username, :email,:phone);\n";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("username", inputdata.getUsername());
        map.addValue("email", inputdata.getEmail());
        map.addValue("phone", inputdata.getPhone());

        this.jdbcTemplate.update(query, map);
    }

    public List<Users> findAll(){
        String query = "SELECT id, username, email, phone\n" +
                "FROM public.\"user\";\n";

        return this.jdbcTemplate.query(query, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user = new Users();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                return user;
            }
        });
    }

    public Optional<Users> findById(Integer id){
        String query = "SELECT id, \"username\", email, phone\n" +
                "FROM public.user where id =:id;\n";

        MapSqlParameterSource map =new MapSqlParameterSource();
        map.addValue("id", id);

        try{
            return this.jdbcTemplate.queryForObject(query, map, new RowMapper<Optional<Users>>() {
                @Override
                public Optional<Users> mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Users user = new Users();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPhone(rs.getString("phone"));
                    return Optional.of(user);
                }
            });

        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public void delete(Integer id){
        String query ="DELETE FROM public.user\n" +
                "WHERE id=:id\n";
        MapSqlParameterSource map = new MapSqlParameterSource("id", id);
        this.jdbcTemplate.update(query, map);
    }

    public void update(Integer id, UsersDto.Save inputData){
        String query ="UPDATE public.user\n" +
                "SET \"username\"= :username , email= :email, phone= :phone\n" +
                "WHERE id=:id\n";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        map.addValue("username", inputData.getUsername());
        map.addValue("email", inputData.getEmail());
        map.addValue("phone", inputData.getPhone());

        this.jdbcTemplate.update(query, map);
    }

    public Users login(UsersDto.Login inputdata){
        String sql = "SELECT * FROM public.user WHERE id = :id AND email = :email";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", inputdata.getId());
        map.addValue("email", inputdata.getEmail());

        List<Users> users = jdbcTemplate.query(sql, map, new RowMapper<Users>() {
            @Override
            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
                Users user =new Users();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        });
        return users.isEmpty() ? null : users.get(0);
    }
}
