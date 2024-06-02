package DevWeb.DevWeb.Service;

import DevWeb.DevWeb.Dto.DadosAtualizacaoUser;
import DevWeb.DevWeb.Model.User;
import DevWeb.DevWeb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity atualizarUser(DadosAtualizacaoUser dadosAtualizacaoUser){
        // Obtenha o nome do usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Busque o usuário pelo nome de usuário (login)
        User user = userRepository.findByLogin(username);

        // Verifique se o usuário foi encontrado
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        // Atualize os dados do usuário, se fornecidos
        if (dadosAtualizacaoUser.login() != null) {
            user.setLogin(dadosAtualizacaoUser.login());
        }else{
            user.setLogin(user.getLogin());
        }
        if (dadosAtualizacaoUser.senha() != null) {
            // Criptografe a nova senha antes de atualizar
            user.setSenha(criptografarSenha(dadosAtualizacaoUser.senha()));
        }else{
            user.setSenha(user.getSenha());
        }

        // Salve as alterações no usuário
        userRepository.save(user);

        // Retorne uma resposta de sucesso
        return ResponseEntity.ok().build();
    }
    private String criptografarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
