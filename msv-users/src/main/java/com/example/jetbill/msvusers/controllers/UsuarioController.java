package com.example.jetbill.msvusers.controllers;


import com.example.jetbill.msvusers.dto.ApiResponseDto;
import com.example.jetbill.msvusers.dto.UserRequestDTO;
import com.example.jetbill.msvusers.exceptions.UserAlreadyExistsException;
import com.example.jetbill.msvusers.exceptions.UserNotFoundException;
import com.example.jetbill.msvusers.exceptions.UserServiceLogicException;
import com.example.jetbill.msvusers.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService userService;

    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO)
            throws UserAlreadyExistsException, UserServiceLogicException {
        return userService.registerUser(userRequestDTO);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<?>> updateUser(@Valid @RequestBody UserRequestDTO userRequestDTO,
                                                        @PathVariable long id)
            throws UserNotFoundException, UserServiceLogicException {
        return userService.updateUser(userRequestDTO, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<?>> deleteUser(@PathVariable long id)
            throws UserNotFoundException, UserServiceLogicException {
        return userService.deleteUser(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto<?>> userDetail(@PathVariable long id)
        throws UserNotFoundException, UserServiceLogicException{
        return userService.userDetail(id);
    }
}
