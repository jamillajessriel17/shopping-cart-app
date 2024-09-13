package com.jamilje.shopping_cart.advice;

import com.jamilje.shopping_cart.dto.ErrorResponseDto;
import com.jamilje.shopping_cart.exception.InsufficientStockException;
import com.jamilje.shopping_cart.exception.NotFoundException;
import com.jamilje.shopping_cart.exception.UnableToSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleNotFoundException(Exception e) {
        return new ErrorResponseDto(getStatusCode(HttpStatus.NOT_FOUND), e.getMessage());
    }

    @ExceptionHandler({UnableToSaveException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleInternalServerError(Exception e) {
        return new ErrorResponseDto(getStatusCode(HttpStatus.INTERNAL_SERVER_ERROR), e.getMessage());
    }
    @ExceptionHandler({InsufficientStockException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleBadRequest(Exception e) {
        return new ErrorResponseDto(getStatusCode(HttpStatus.BAD_REQUEST), e.getMessage());
    }

    private String getStatusCode(HttpStatus httpStatus) {
        return String.valueOf(httpStatus.value());
    }
}
