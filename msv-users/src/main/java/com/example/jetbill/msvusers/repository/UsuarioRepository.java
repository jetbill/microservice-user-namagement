package com.example.jetbill.msvusers.repository;

import com.example.jetbill.msvusers.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Usuario findByUserName(String name);
    List<Usuario> findAllByOrderByRegDateTimeDesc();

}
