package by.feedblog.api.repository;

import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;

import java.util.List;

public interface DislikeDao {
    void add(Dislike dislike);

    void deleteById(int id);

    Dislike getById(int id);

    List<Dislike> getAll();

    List<Dislike> getAllByUser(User user);

    List<Dislike> getAllByPost(Post post);

    boolean contains(User user, Post post);

    boolean containsById(int id);
}
