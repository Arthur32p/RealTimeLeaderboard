package io.github.arthur32p.Real_time_Leaderboard.dto;

import io.github.arthur32p.Real_time_Leaderboard.models.Jogo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RegistroPontuacaoRequestDto(
        @NotNull(message = "O jogo é obrigatorio")
        Jogo jogo,

        @PositiveOrZero(message = "A pontuacao não pode ser negativa")
        int score
) {
}
