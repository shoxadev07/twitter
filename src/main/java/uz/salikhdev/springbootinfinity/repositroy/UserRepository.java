package uz.salikhdev.springbootinfinity.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springbootinfinity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
