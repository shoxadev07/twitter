package uz.salikhdev.springbootinfinity.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springbootinfinity.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmailAndCreatedAt(String email, LocalDateTime createdAt);

}
