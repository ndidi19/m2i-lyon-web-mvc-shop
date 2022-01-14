package com.m2ilyon.mongoshop.exception;

import com.m2ilyon.mongoshop.dto.CreateUserDto;

public class UserAlreadyExistsException extends RuntimeException {
    private CreateUserDto userDto;

    public UserAlreadyExistsException(String message, CreateUserDto userDto) {
        super(message);
        this.userDto = userDto;
    }

    public CreateUserDto getUserDto() {
        return userDto;
    }
}
