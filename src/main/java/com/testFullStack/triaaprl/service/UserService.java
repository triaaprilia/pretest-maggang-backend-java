package com.testFullStack.triaaprl.service;

import com.testFullStack.triaaprl.dao.UserDao;
import com.testFullStack.triaaprl.dao.UserProductDao;
import com.testFullStack.triaaprl.dto.UsersDto;
import com.testFullStack.triaaprl.entity.Users;
import com.testFullStack.triaaprl.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao dao;
    private final UserProductDao userProductDao;

    public void save(UsersDto.Save data){
        this.dao.save(data);
    }

    public List<Users> findAll(){
        return this.dao.findAll();
    }

    public Users findById(Integer id){
        return this.dao.findById(id).orElseThrow(() ->
                new IdNotFoundException("User dengan id " + id + " tidak ditemukan"));
    }

    public Users login(UsersDto.Login data){return this.dao.login(data);}

    public void delete(Integer id){
        this.userProductDao.deleteByUser(id);
        this.dao.delete(id);

    }


    public void update(Integer id, UsersDto.Save data){
        findById(id);
        this.dao.update(id, data);
    }
}
