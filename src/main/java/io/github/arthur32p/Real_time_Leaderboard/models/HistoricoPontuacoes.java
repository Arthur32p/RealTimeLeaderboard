package io.github.arthur32p.Real_time_Leaderboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "historico_pontuacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoPontuacoes {

    @Id
    private String id;
    private String usuarioId;
    private Jogo jogo;
    private int score;
    private LocalDateTime dataHora;


}
