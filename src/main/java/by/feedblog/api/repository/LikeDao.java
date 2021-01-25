package by.feedblog.api.repository;

import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;

import java.util.List;

public interface LikeDao {
    void add(Like like);

    void deleteById(int id);

    Like getById(int id);

    List<Like> getAll();

    List<Like> getAllByUser(User user);

    List<Like> getAllByPost(Post post);

    boolean contains(User user, Post post);

    boolean containsById(int id);
}
