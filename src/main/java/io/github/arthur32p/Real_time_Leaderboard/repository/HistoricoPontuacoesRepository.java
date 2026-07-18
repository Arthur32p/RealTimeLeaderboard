package io.github.arthur32p.Real_time_Leaderboard.repository;

import io.github.arthur32p.Real_time_Leaderboard.models.HistoricoPontuacoes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoricoPontuacoesRepository extends MongoRepository<HistoricoPontuacoes, String> {
}
