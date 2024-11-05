package com.example.usuario.projetofinal.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UsuarioRequestDTO {

    private String nome;

    @JsonProperty(required = true)
    private String email;

    private String senha;

}