package com.example.jetbill.msvusers.services;

import com.example.jetbill.msvusers.dto.ApiResponseDto;
import com.example.jetbill.msvusers.dto.ApiResponseStatus;
import com.example.jetbill.msvusers.dto.UserRequestDTO;
import com.example.jetbill.msvusers.exceptions.UserAlreadyExistsException;
import com.example.jetbill.msvusers.exceptions.UserNotFoundException;
import com.example.jetbill.msvusers.exceptions.UserServiceLogicException;
import com.example.jetbill.msvusers.models.entity.Usuario;
import com.example.jetbill.msvusers.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService{
    private final UsuarioRepository userRepository;

    public UsuarioServiceImpl(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> registerUser(UserRequestDTO newUserDetails)
            throws UserAlreadyExistsException, UserServiceLogicException {
        try {
            if (userRepository.findByEmail(newUserDetails.getEmail()) != null) {
                throw new UserAlreadyExistsException("Registration failed: User already exists with email "
                        + newUserDetails.getEmail());
            }
            if (userRepository.findByUserName(newUserDetails.getUserName()) != null) {
                throw new UserAlreadyExistsException("Registration failed: User already exists with username "
                        + newUserDetails.getUserName());
            }

            Usuario newUser = Usuario.builder()
                    .userName(newUserDetails.getUserName())
                    .email(newUserDetails.getEmail())
                    .password(newUserDetails.getPassword())
                    .regDateTime(LocalDateTime.now())
                    .build();

            // save() is an in built method given by JpaRepository
            userRepository.save(newUser);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
                            "New user  has been successfully created!"));

        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to create new user : " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> getAllUsers() throws UserServiceLogicException {
            try {
                List<Usuario> users = userRepository.findAllByOrderByRegDateTimeDesc();

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(), users)
                        );

            }catch (Exception e) {
                log.error("Failed to fetch all users: " + e.getMessage());
                throw new UserServiceLogicException();
            }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> updateUser(UserRequestDTO newUserDetails, long id)
        throws UserNotFoundException, UserServiceLogicException {
        try {
            Usuario user = userRepository.findById(id).orElseThrow(
                    () -> new UserNotFoundException("User not found with id " + id));

            user.setEmail(newUserDetails.getEmail());
            user.setUserName(newUserDetails.getUserName());
            user.setPassword(newUserDetails.getPassword());

            userRepository.save(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
                            "User  updated successfully!")
                    );

        }catch(UserNotFoundException e){
            throw new UserNotFoundException(e.getMessage());
        }catch(Exception e) {
            log.error("Failed to update user : " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> deleteUser(long id)
            throws UserServiceLogicException, UserNotFoundException {
        try {
            Usuario user = userRepository.findById(id).orElseThrow(
                    () -> new UserNotFoundException("User not found with id " + id));

            userRepository.delete(user);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDto<>(ApiResponseStatus.SUCCESS.name(),
                            "User  deleted successfully!")
                    );
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error("Failed to delete user: " + e.getMessage());
            throw new UserServiceLogicException();
        }
    }
}


