package io.github.arthur32p.Real_time_Leaderboard.controller;

import io.github.arthur32p.Real_time_Leaderboard.dto.RegistroPontuacaoRequestDto;
import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import io.github.arthur32p.Real_time_Leaderboard.service.HistoricoPontuacoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scores")
@RequiredArgsConstructor
public class PontuacaoController {

    private final HistoricoPontuacoesService service;

    @PostMapping
    public ResponseEntity registrarPontos(@AuthenticationPrincipal Usuario usuario, @RequestBody RegistroPontuacaoRequestDto dto){
        service.registrarPontos(usuario.getId(), dto.jogo(), dto.score());

        return ResponseEntity.ok().build();
    }

}
