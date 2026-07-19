package io.github.arthur32p.Real_time_Leaderboard.service;

import io.github.arthur32p.Real_time_Leaderboard.models.HistoricoPontuacoes;
import io.github.arthur32p.Real_time_Leaderboard.models.Jogo;
import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import io.github.arthur32p.Real_time_Leaderboard.repository.HistoricoPontuacoesRepository;
import io.github.arthur32p.Real_time_Leaderboard.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HistoricoPontuacoesServiceTest {

    @Mock
    private HistoricoPontuacoesRepository historicoPontuacoesRepository;

    @Mock
    private StringRedisTemplate redisTemplate;

    @Mock
    private ZSetOperations<String, String> zSetOperations;

    @InjectMocks
    private HistoricoPontuacoesService historicoPontuacoesService;

    @Test
    void registrarPontos() {

        String usuarioId = "123";
        Usuario usuarioTeste = new Usuario();
        usuarioTeste.setId(usuarioId);
        usuarioTeste.setNickname("Teste");

        when(redisTemplate.opsForZSet()).thenReturn(zSetOperations);

        historicoPontuacoesService.registrarPontos(usuarioId, Jogo.JOGO_DA_VELHA, 150);

        verify(historicoPontuacoesRepository, times(1)).save(any(HistoricoPontuacoes.class));

        verify(zSetOperations, times(1)).incrementScore(eq("JOGO_DA_VELHA"), eq(usuarioId), eq(150.0));
    }
}