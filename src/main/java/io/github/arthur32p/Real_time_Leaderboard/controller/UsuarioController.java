package io.github.arthur32p.Real_time_Leaderboard.controller;

import io.github.arthur32p.Real_time_Leaderboard.dto.LoginRequestDto;
import io.github.arthur32p.Real_time_Leaderboard.dto.UserResponseDto;
import io.github.arthur32p.Real_time_Leaderboard.dto.UsuarioRequestDto;
import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import io.github.arthur32p.Real_time_Leaderboard.security.TokenService;
import io.github.arthur32p.Real_time_Leaderboard.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        UserResponseDto logado = new UserResponseDto(token);

        return ResponseEntity.ok(logado);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRequestDto dto){
        Usuario salvo = service.register(dto);

        return ResponseEntity.ok().build();
    }

}
