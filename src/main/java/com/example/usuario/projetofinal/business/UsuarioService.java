package com.example.usuario.projetofinal.business;

import com.example.usuario.projetofinal.api.converter.UsuarioConverter;
import com.example.usuario.projetofinal.api.converter.UsuarioMapper;
import com.example.usuario.projetofinal.api.request.UsuarioRequestDTO;
import com.example.usuario.projetofinal.api.response.UsuarioResponseDTO;
import com.example.usuario.projetofinal.infrastructure.entity.UsuarioEntity;
import com.example.usuario.projetofinal.infrastructure.exceptions.BusinessException;
import com.example.usuario.projetofinal.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.util.Assert.notNull;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final UsuarioMapper usuarioMapper;

    public UsuarioEntity salvaUsuario(UsuarioEntity usuarioEntity) {
        return usuarioRepository.save(usuarioEntity);
    }

    public UsuarioResponseDTO gravarUsuarios(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");
            UsuarioEntity usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));
            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity);
        } catch (Exception e) {
            throw new BusinessException("Erro ao gravar dados de usuário", e);
        }
    }

    public UsuarioResponseDTO buscaDadosUsuario(String email) {
        try {
            UsuarioEntity entity = usuarioRepository.findByEmail(email);
            notNull(entity, "Usuário não encontrado");

            return usuarioMapper.paraUsuarioResponseDTO(entity);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar dados de usuário", e);
        }
    }

    @Transactional
    public void deletaDadosUsuario(String email) {
        try {
            usuarioRepository.deleteByEmail(email);
        } catch (Exception e) {
            throw new BusinessException("Erro ao deletar dados de usuário", e);
        }
    }

    public List<UsuarioResponseDTO> buscaTodosUsuarios() {
        List<UsuarioEntity> entities = usuarioRepository.findAll();
        return usuarioMapper.paraListaUsuarioResponseDTO(entities);
    }

    @Transactional
    public UsuarioResponseDTO login(String email, String senha) {
        try {
            UsuarioEntity entity = usuarioRepository.findByEmailAndSenha(email, senha);
            notNull(entity, "Usuário não encontrado");

            return usuarioMapper.paraUsuarioResponseDTO(entity);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar dados de usuário", e);
        }
    }


}