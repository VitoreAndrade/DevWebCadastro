package DevWeb.DevWeb.Dto;

import DevWeb.DevWeb.Model.User;

public record DadosListagemLogin(
        String login
) {
    public DadosListagemLogin (User user){
        this(user.getUsername());
    }

}

