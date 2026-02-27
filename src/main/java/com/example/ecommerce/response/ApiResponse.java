package com.example.ecommerce.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.success = true;
        this.message = message;
        
    }

    public ApiResponse(String message){
        this.success = false;
        this.message = message;
    }

}
