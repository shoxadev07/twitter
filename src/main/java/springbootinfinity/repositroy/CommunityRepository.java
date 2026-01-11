package springbootinfinity.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootinfinity.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

}
