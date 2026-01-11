package springbootinfinity.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootinfinity.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    boolean existsByContentUrl(String contentUrl);

    List<Post> findAllByOrderByCreatedAtDesc();

}
