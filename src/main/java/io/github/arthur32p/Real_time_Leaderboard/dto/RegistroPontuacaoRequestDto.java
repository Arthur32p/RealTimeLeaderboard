package io.github.arthur32p.Real_time_Leaderboard.dto;

import io.github.arthur32p.Real_time_Leaderboard.models.Jogo;

public record RegistroPontuacaoRequestDto(
        Jogo jogo,
        int score
) {
}
