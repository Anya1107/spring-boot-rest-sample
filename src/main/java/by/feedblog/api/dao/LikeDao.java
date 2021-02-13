package by.feedblog.api.dao;

import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;

import java.util.List;

public interface LikeDao {

    void deleteById(int id);

    Like getById(int id);

    List<Like> getAll();

    List<Like> getAllByUser(User user);

    boolean contains(User user);

    boolean containsById(int id);
}
