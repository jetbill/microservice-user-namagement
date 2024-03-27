package com.example.jetbill.msvusers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Username is required!")
    @Size(min= 3, message = "Username must have atleast 3 characters!")
    @Size(max= 20, message = "Username can have have atmost 20 characters!")
    private String userName;
    @Email(message = "Email is not in valid format!")
    @NotBlank(message = "Email is required!")
    private String email;
    @NotBlank(message = "Password  is required!")
    @Size(min = 6, max = 12, message = "Password must have 6 characters!")
    private String password;
}
