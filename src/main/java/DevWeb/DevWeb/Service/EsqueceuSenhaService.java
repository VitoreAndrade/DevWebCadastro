package DevWeb.DevWeb.Service;
import DevWeb.DevWeb.Model.User;
import DevWeb.DevWeb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EsqueceuSenhaService {

    @Autowired
    private UserRepository userRepository;

    public boolean redefinirSenha(String login, String senhaNova){
        User user = userRepository.findByLogin(login);
        if(user != null){
            user.setSenha(criptografarSenha(senhaNova));
            userRepository.save(user);
            return true;

        }
        return false;

    }
    private String criptografarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }

}
