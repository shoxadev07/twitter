package uz.salikhdev.springbootinfinity.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.salikhdev.springbootinfinity.entity.Comment;
import uz.salikhdev.springbootinfinity.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

}
