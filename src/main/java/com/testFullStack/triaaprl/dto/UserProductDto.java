package com.testFullStack.triaaprl.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class UserProductDto {
    @Data
    public static class Save{
        private Integer id;

        @NotNull(message = "Tidak boleh null")
        private Integer userId;

        @NotNull(message = "Tidak boleh null")
        private Integer productId;

        @Min(0)
        private Integer quantity;
    }
}
