package Group.Better.repository;

import Group.Better.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}


