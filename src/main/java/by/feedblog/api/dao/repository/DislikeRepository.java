package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeRepository extends JpaRepository<Dislike, Integer> {
    boolean existsByUser(User user);
    boolean existsByUserAndPost(User user, Post post);
    List<Dislike> findAllByUser(User user);
//    List<Dislike> findAllByPost(Post post);
}
