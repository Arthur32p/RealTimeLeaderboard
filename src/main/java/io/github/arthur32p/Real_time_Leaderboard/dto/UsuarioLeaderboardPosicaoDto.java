package io.github.arthur32p.Real_time_Leaderboard.dto;

public record UsuarioLeaderboardPosicaoDto (
    String nickname,
    Integer posicao,
    Double pontos,
    String jogo
) { }
