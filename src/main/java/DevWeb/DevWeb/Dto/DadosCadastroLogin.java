package DevWeb.DevWeb.Dto;

import jakarta.validation.constraints.NotBlank;


public record DadosCadastroLogin(
        @NotBlank
        String login,
        @NotBlank
        String senha


) {
}

