package io.github.arthur32p.Real_time_Leaderboard.service;

import io.github.arthur32p.Real_time_Leaderboard.models.HistoricoPontuacoes;
import io.github.arthur32p.Real_time_Leaderboard.models.Jogo;
import io.github.arthur32p.Real_time_Leaderboard.repository.HistoricoPontuacoesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistoricoPontuacoesService {

    private final HistoricoPontuacoesRepository repository;
    private final StringRedisTemplate template;

    public void registrarPontos(String usuarioId, Jogo jogo, int score){
        HistoricoPontuacoes historicoPontuacoes = new HistoricoPontuacoes();
        historicoPontuacoes.setUsuarioId(usuarioId);
        historicoPontuacoes.setJogo(jogo);
        historicoPontuacoes.setScore(score);
        historicoPontuacoes.setDataHora(LocalDateTime.now());

        repository.save(historicoPontuacoes);

        template.opsForZSet().incrementScore(jogo.name(), usuarioId, score);
    }

}
