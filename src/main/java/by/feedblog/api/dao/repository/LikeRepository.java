package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Integer> {
    boolean existsByUser(User user);
    List<Like> findAllByUser(User user);
    boolean existsByUserAndPost(User user, Post post);
//    List<Like> findAllByPost(Post post);
}
