package io.github.arthur32p.Real_time_Leaderboard.repository;

import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    UserDetails findByUsername(String username);

}
