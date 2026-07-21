package io.github.arthur32p.Real_time_Leaderboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDto(
        @NotBlank(message = "O nickname é obrigatório")
        @Size(min = 3, max = 30, message = "O nickname deve ter entre 3 e 30 caracteres")
        String nickname,

        @NotBlank(message = "O username é obrigatório")
        @Size(min = 3, max = 20, message = "O username deve ter entre 3 e 20 caracteres")
        String username,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 50, message = "A senha deve ter no minimo 6 caracteres")
        String password
) {
}
