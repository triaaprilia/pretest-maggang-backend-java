package com.testFullStack.triaaprl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    private Integer id;
    private String username;
    private String email;
    private String phone;
}
