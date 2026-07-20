package io.github.arthur32p.Real_time_Leaderboard.script;

import io.github.arthur32p.Real_time_Leaderboard.models.Jogo;
import io.github.arthur32p.Real_time_Leaderboard.models.Usuario;
import io.github.arthur32p.Real_time_Leaderboard.repository.UsuarioRepository;
import io.github.arthur32p.Real_time_Leaderboard.service.HistoricoPontuacoesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@Component
public class CargaDadosDataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final HistoricoPontuacoesService historicoPontuacoesService;
    private final PasswordEncoder passwordEncoder;

    public CargaDadosDataLoader(UsuarioRepository usuarioRepository,
                                HistoricoPontuacoesService historicoPontuacoesService,
                                PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.historicoPontuacoesService = historicoPontuacoesService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            System.out.println("Criando usuarios base...");
            criarUsuariosBase();
        }

        List<Usuario> usuarios = usuarioRepository.findAll();
        Random random = new Random();

        for (Jogo jogo : Jogo.values()) {
            popularPontuacoesParaJogo(jogo, usuarios, random);
        }
    }

    private void criarUsuariosBase() {
        List<String> apelidos = List.of(
                "LucasSilva", "GabrielSantos", "MatheusOliveira", "MarianaCosta",
                "BeatrizSouza", "RodrigoAlmeida", "CamilaPereira", "FelipeRodrigues",
                "JulianaFerreira", "RafaelCarvalho"
        );

        for (String nickname : apelidos) {
            Usuario usuario = new Usuario();
            usuario.setUsername(nickname.toLowerCase());
            usuario.setNickname(nickname);
            usuario.setPassword(passwordEncoder.encode("123456"));
            usuarioRepository.save(usuario);
        }
        System.out.println("10 usuarios base criados com sucesso.");
    }

    private void popularPontuacoesParaJogo(Jogo jogo, List<Usuario> usuarios, Random random) {

        System.out.println("Gerando pontuacoes para o jogo: " + jogo.name());

        for (Usuario usuario : usuarios) {
            int quantidadePontuacoes = random.nextInt(3) + 1;
            for (int i = 0; i < quantidadePontuacoes; i++) {
                int pontos = 50 + random.nextInt(950);

                historicoPontuacoesService.registrarPontos(
                        usuario.getId(),
                        jogo,
                        pontos
                );
            }
        }
    }
}