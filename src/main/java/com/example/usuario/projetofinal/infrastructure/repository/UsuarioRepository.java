package com.example.usuario.projetofinal.infrastructure.repository;

import com.example.usuario.projetofinal.infrastructure.entity.UsuarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {

    UsuarioEntity findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    //metodo para buscar usuario por email e senha
    UsuarioEntity findByEmailAndSenha(String email, String senha);

    // Método para verificar se o e-mail já existe no banco de dados
    boolean existsByEmail(String email);


}
