package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Comment;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Integer> {
//    List<Comment> findAllByPost(int id);
//    List<Comment> findAllByUser(User user);
}
