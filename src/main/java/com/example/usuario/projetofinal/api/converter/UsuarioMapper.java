package com.example.usuario.projetofinal.api.converter;


import com.example.usuario.projetofinal.api.response.UsuarioResponseDTO;
import com.example.usuario.projetofinal.infrastructure.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", source = "usuario.id")
    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "senha", source = "usuario.senha")
    @Mapping(target = "dataCadastro", source = "usuario.dataCadastro")
    UsuarioResponseDTO paraUsuarioResponseDTO(UsuarioEntity usuario);
    List<UsuarioResponseDTO> paraListaUsuarioResponseDTO(List<UsuarioEntity> usuarios);


}