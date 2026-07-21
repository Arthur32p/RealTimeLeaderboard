package io.github.arthur32p.Real_time_Leaderboard.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "Campo obrigatório")
        String username,
        @NotBlank(message = "Campo obrigatório")
        String password
) {
}
