package io.github.arthur32p.Real_time_Leaderboard.service;

import io.github.arthur32p.Real_time_Leaderboard.dto.UsuarioRequestDto;
import io.github.arthur32p.Real_time_Leaderboard.exception.UsuarioJaCadastradoException;
import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import io.github.arthur32p.Real_time_Leaderboard.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public Usuario register(UsuarioRequestDto dto){
        if (repository.findByUsername(dto.username()) != null) {
            throw new UsuarioJaCadastradoException("Este username já está em uso!");
        }

        Usuario salvo = new Usuario();
        String encodePassword = new BCryptPasswordEncoder().encode(dto.password());
        salvo.setNickname(dto.nickname());
        salvo.setUsername(dto.username());
        salvo.setPassword(encodePassword);

        return repository.save(salvo);
    }
}
