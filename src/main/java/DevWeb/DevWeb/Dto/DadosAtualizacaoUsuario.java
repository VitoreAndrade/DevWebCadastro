package DevWeb.DevWeb.Dto;

public record DadosAtualizacaoUsuario(
        Long id,
        String nome,
        String email,
        String telefone,
        int idade
) {
}

