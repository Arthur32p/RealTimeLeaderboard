package io.github.arthur32p.Real_time_Leaderboard.service;

import io.github.arthur32p.Real_time_Leaderboard.dto.LeaderboardGeralDto;
import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import io.github.arthur32p.Real_time_Leaderboard.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final StringRedisTemplate redisTemplate;
    private final UsuarioRepository usuarioRepository;

    public List<LeaderboardGeralDto> getTopLeaderboard(String jogo){
        Set<ZSetOperations.TypedTuple<String>> pontuacoes = redisTemplate.opsForZSet().reverseRangeWithScores(jogo, 0, -1);

        List<String> ids = new ArrayList<>();
        Map<String, Double> pontuacoesPorUsuario = new HashMap<>();
        if(pontuacoes != null){
            for(ZSetOperations.TypedTuple<String> tupla : pontuacoes){
                ids.add(tupla.getValue());
                pontuacoesPorUsuario.put(tupla.getValue(), tupla.getScore());
            }
        }

        List<Usuario> usuarios = usuarioRepository.findAllById(ids);

        Map<String, String> nicknamePorUsuario = new HashMap<>();
        for(Usuario usuario : usuarios){
            nicknamePorUsuario.put(usuario.getId(), usuario.getNickname());
        }

        List<LeaderboardGeralDto> resultado = new ArrayList<>();
        int posicao = 1;
        for(String usuarioId : ids){
            String nickaname = nicknamePorUsuario.get(usuarioId);
            Double pontos = pontuacoesPorUsuario.get(usuarioId);
            LeaderboardGeralDto dto = new LeaderboardGeralDto(posicao, nickaname, pontos);
            resultado.add(dto);
            posicao++;
        }

        return resultado;
    }

}
