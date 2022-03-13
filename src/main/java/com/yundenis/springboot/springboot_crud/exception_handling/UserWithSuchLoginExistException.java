package com.yundenis.springboot.springboot_crud.exception_handling;

import org.springframework.dao.DataIntegrityViolationException;

public class UserWithSuchLoginExistException extends DataIntegrityViolationException {
    public UserWithSuchLoginExistException(String msg) {
        super(msg);
    }
}
