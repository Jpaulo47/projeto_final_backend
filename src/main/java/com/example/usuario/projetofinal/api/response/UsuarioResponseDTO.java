package com.example.usuario.projetofinal.api.response;

public record UsuarioResponseDTO(String id,

                                 String nome,

                                 String email,

                                 String senha,
                                 String dataCadastro
) {
}