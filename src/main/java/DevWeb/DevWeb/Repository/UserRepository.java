package DevWeb.DevWeb.Repository;


import DevWeb.DevWeb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    User findByLogin(String login);

}


