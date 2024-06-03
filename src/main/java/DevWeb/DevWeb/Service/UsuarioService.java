package DevWeb.DevWeb.Service;

import DevWeb.DevWeb.Dto.DadosAtualizacaoUsuario;
import DevWeb.DevWeb.Dto.DadosListagemUsuario;
import DevWeb.DevWeb.Model.User;
import DevWeb.DevWeb.Repository.RacaoRepository;
import DevWeb.DevWeb.Repository.UserRepository;
import DevWeb.DevWeb.Dto.DadosCadastroUsuario;
import DevWeb.DevWeb.Model.Usuario;
import DevWeb.DevWeb.Repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RacaoRepository racaoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutenticacaoService autenticacaoService;


    public ResponseEntity cadastrarUsuario(DadosCadastroUsuario dadosCadastroUsuario) {
        var usuario = new Usuario(dadosCadastroUsuario);

        var user = userRepository.findByLogin(dadosCadastroUsuario.user().login());
        if (user == null || user.getUsername().isEmpty()) {
            var cadastrar = usuarioRepository.save(usuario);
            return ResponseEntity.ok().body(cadastrar);
        } else {

            throw new RuntimeException("Login já em uso");
        }
    }
    public Page<DadosListagemUsuario> listarUsuario(Pageable lista) {
        return usuarioRepository.findAllByAtivoTrue(lista).map(DadosListagemUsuario::new);
    }


    public ResponseEntity<?> atualizarUsuario(String username, DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {
        Long idUsuario = autenticacaoService.getIdUsuarioPorLogin(username);
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + idUsuario));
        if (dadosAtualizacaoUsuario.nome() != null) {
            usuario.setNome(dadosAtualizacaoUsuario.nome());
        }
        else {
            usuario.setNome(usuario.getNome());
        }
        if (dadosAtualizacaoUsuario.email() != null) {
            usuario.setEmail(dadosAtualizacaoUsuario.email());
        }
        else{
            usuario.setEmail(usuario.getEmail());
        }
        if (dadosAtualizacaoUsuario.idade() > 0) {
            usuario.setIdade(dadosAtualizacaoUsuario.idade());
        }
        else {
            usuario.setIdade(usuario.getIdade());
        }
        if(dadosAtualizacaoUsuario.telefone() != null){
            usuario.setTelefone(dadosAtualizacaoUsuario.telefone());
        }
        else{
            usuario.setTelefone(usuario.getTelefone());
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().body(usuario);
    }
    public ResponseEntity excluirUsuario(Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.excluir();
        return ResponseEntity.noContent().build();
    }

    public List<User> listarUsuarios() {
        return userRepository.findAll();
    }

    public Long getIdUsuarioPorIdLogin(Long idLogin) {
        Usuario usuario = usuarioRepository.findByUsuarioId(idLogin);
        if (usuario != null) {
            return usuario.getId();
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID do login: " + idLogin);
        }
    }
}