package by.feedblog.api.repository;



import by.feedblog.api.entity.Comment;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;

import java.util.List;

public interface CommentDao {
    void save(Comment comment);

    void deleteById(int id);

    Comment getById(int id);

    List<Comment> getAllByPostId(int id);

    List<Comment> getAllByUser(User user);

    List<Comment> getAll();

    boolean containsById(int id);
}
