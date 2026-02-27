package com.example.ecommerce.controller;

import com.example.ecommerce.response.ApiResponse;

public abstract class BaseController {

    protected <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    protected ApiResponse<Void> success(String message) {
        return new ApiResponse<>(message);
    }

    protected ApiResponse<Void> error(String message) {
        return new ApiResponse<>(message);
    }
}