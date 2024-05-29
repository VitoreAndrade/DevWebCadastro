package DevWeb.DevWeb.Repository;

import DevWeb.DevWeb.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findAllByAtivoTrue (Pageable lista);

    @Query("SELECT u FROM Usuario u WHERE u.usuario.id = :idLogin")
    Usuario findByUsuarioId(@Param("idLogin") Long idLogin);

}
