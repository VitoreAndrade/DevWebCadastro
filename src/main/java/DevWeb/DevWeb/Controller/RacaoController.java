package DevWeb.DevWeb.Controller;
import DevWeb.DevWeb.Dto.DadosAtualizacaoRacao;
import DevWeb.DevWeb.Dto.DadosCadastrosRacao;
import DevWeb.DevWeb.Dto.DadosListagemRacao;
import DevWeb.DevWeb.Model.User;
import DevWeb.DevWeb.Repository.UserRepository;
import DevWeb.DevWeb.Service.RacaoService;
import DevWeb.DevWeb.Service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("racao")
    @CrossOrigin(origins = "http://localhost:8081/")
    public class RacaoController {

        @Autowired
        private RacaoService racaoService;
        @Autowired
        private TokenService tokenService;
        @Autowired
        private UserRepository userRepository;

        @Transactional
        @PostMapping
        public ResponseEntity cadastrarRacao(@RequestBody @Valid DadosCadastrosRacao dados, HttpServletRequest request) {
            String tokenJWT = request.getHeader("Authorization").replace("Bearer ", "");
            String username = tokenService.getSubject(tokenJWT);
            User user = userRepository.findByLogin(username);
            Long idUsuario = user.getId();
            return racaoService.cadastrarRacao(dados, idUsuario);
        }

        @GetMapping
        public Page<DadosListagemRacao> listarRacao(HttpServletRequest request, @PageableDefault(size = 30, sort = {"nome"}) Pageable lista) {
            return racaoService.listarRacao(request, lista);
        }

        @PutMapping
        @Transactional
        public ResponseEntity atualizarRacao(@RequestBody @Valid DadosAtualizacaoRacao dadosAtualizacao){
            return racaoService.atualizarRacao(dadosAtualizacao);
        }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity excluirRacao (@PathVariable Long id){
            return racaoService.excluirRacao(id);
        }
    }


