package com.testFullStack.triaaprl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UsersDto {
    @Data
    public static class Save{
        @NotEmpty(message = "String tidak boleh kosong")
        @NotNull(message = "Tidak boleh null")
        private String username;

        @Email
        private String email;

        @Size(min = 11, max = 13)
        private String phone;

        }
    @Data
    public static class Login{
        @NotNull
        private Integer id;
        @NotEmpty
        private String name;
        @NotEmpty
        private String email;
    }
}
