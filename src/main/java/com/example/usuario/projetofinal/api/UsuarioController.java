package com.example.usuario.projetofinal.api;

import com.example.usuario.projetofinal.api.request.UsuarioRequestDTO;
import com.example.usuario.projetofinal.api.response.UsuarioResponseDTO;
import com.example.usuario.projetofinal.business.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioResponseDTO> gravaDadosUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(usuarioService.gravarUsuarios(usuarioRequestDTO));
    }


    @GetMapping()
    public ResponseEntity<UsuarioResponseDTO> buscaUsuarioPorEmail(@RequestParam ("email") String email) {
        return ResponseEntity.ok(usuarioService.buscaDadosUsuario(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaDadosUsuario(@RequestParam ("email") String email) {
        usuarioService.deletaDadosUsuario(email);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> buscaTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.buscaTodosUsuarios());
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestParam ("email") String email, @RequestParam ("senha") String senha) {
        return ResponseEntity.ok(usuarioService.login(email, senha));
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDTO> atualizaUsuario(@RequestParam ("email") String email, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(usuarioService.atualizaUsuario(usuarioRequestDTO));
    }

}