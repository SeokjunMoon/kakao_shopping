package com.kakao.shopping._core.errors.exception;

import com.kakao.shopping._core.utils.ApiUtils;
import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException implements CustomException{
    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public ApiUtils.ApiResult<?> body() {
        return ApiUtils.error(getMessage(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
