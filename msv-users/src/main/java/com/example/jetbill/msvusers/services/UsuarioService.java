package com.example.jetbill.msvusers.services;

import com.example.jetbill.msvusers.dto.ApiResponseDto;
import com.example.jetbill.msvusers.dto.UserRequestDTO;
import com.example.jetbill.msvusers.exceptions.UserAlreadyExistsException;
import com.example.jetbill.msvusers.exceptions.UserNotFoundException;
import com.example.jetbill.msvusers.exceptions.UserServiceLogicException;
import org.springframework.http.ResponseEntity;


public interface UsuarioService {
    ResponseEntity<ApiResponseDto<?>> registerUser(UserRequestDTO newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> getAllUsers()
            throws UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> updateUser(UserRequestDTO newUserDetails, long id)
            throws UserNotFoundException, UserServiceLogicException;

    ResponseEntity<ApiResponseDto<?>> deleteUser(long id)
            throws UserServiceLogicException, UserNotFoundException;
    ResponseEntity<ApiResponseDto<?>> userDetail(long id) throws UserNotFoundException, UserServiceLogicException;
}
