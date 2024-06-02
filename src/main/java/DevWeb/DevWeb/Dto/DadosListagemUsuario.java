package DevWeb.DevWeb.Dto;

import DevWeb.DevWeb.Model.Usuario;

public record DadosListagemUsuario(
        Long id,
        String nome,
        String email,
        String telefone,

        int idade


) {
    public DadosListagemUsuario (Usuario usuario){
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getIdade());
    }
}
