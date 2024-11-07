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
            // Verifica se os dados do usuário são válidos
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");

            // Verifica se o e-mail já está registrado no banco de dados
            if (usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
                throw new BusinessException("E-mail já registrado.");
            }

            // Converte o DTO para entidade e salva no banco de dados
            UsuarioEntity usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));

            // Retorna o DTO de resposta
            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity);
        } catch (BusinessException be) {
            throw be;  // Lança a exceção de negócio
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

    // Método para atualizar os dados do usuário
    @Transactional
    public UsuarioResponseDTO atualizaUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        try {
            // Verifica se os dados do usuário são válidos
            notNull(usuarioRequestDTO, "Os dados do usuário são obrigatórios");

            // Verifica se o e-mail já está registrado no banco de dados
            if (!usuarioRepository.existsByEmail(usuarioRequestDTO.getEmail())) {
                throw new BusinessException("E-mail não registrado.");
            }

            // Converte o DTO para entidade e salva no banco de dados
            UsuarioEntity usuarioEntity = salvaUsuario(usuarioConverter.paraUsuarioEntity(usuarioRequestDTO));

            // Retorna o DTO de resposta
            return usuarioMapper.paraUsuarioResponseDTO(usuarioEntity);
        } catch (BusinessException be) {
            throw be;  // Lança a exceção de negócio
        } catch (Exception e) {
            throw new BusinessException("Erro ao atualizar dados de usuário", e);
        }
    }


}