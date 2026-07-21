package io.github.arthur32p.Real_time_Leaderboard.controller;

import io.github.arthur32p.Real_time_Leaderboard.dto.LeaderboardGeralDto;
import io.github.arthur32p.Real_time_Leaderboard.dto.UsuarioLeaderboardPosicaoDto;
import io.github.arthur32p.Real_time_Leaderboard.models.Jogo;
import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import io.github.arthur32p.Real_time_Leaderboard.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService service;

    @GetMapping("/{jogo}")
    public ResponseEntity<List<LeaderboardGeralDto>> getLeaderboard(@PathVariable Jogo jogo){
        List<LeaderboardGeralDto> resultado = service.getTopLeaderboard(jogo.name());

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{jogo}/me")
    public ResponseEntity<UsuarioLeaderboardPosicaoDto> getPositionLeaderBoard(@AuthenticationPrincipal Usuario usuario, @PathVariable Jogo jogo){
        UsuarioLeaderboardPosicaoDto resultado = service.getPositionLeaderboard(usuario.getId(), jogo.name());

        return ResponseEntity.ok(resultado);
    }
}
