package src.main.java.com.DL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.java.com.BL.entities.User;

import java.util.Optional;


//@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findByGithubUsername(String githubUsername);

    Optional<User> findByEmailAndPassword(String email,String password);

}
