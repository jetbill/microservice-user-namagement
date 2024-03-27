package com.example.jetbill.msvusers.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String userName;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime regDateTime;


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
