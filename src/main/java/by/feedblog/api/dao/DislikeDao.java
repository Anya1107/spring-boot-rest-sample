package by.feedblog.api.dao;

import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;

import java.util.List;

public interface DislikeDao {
    void add(Dislike dislike);

    void deleteById(int id);

    Dislike getById(int id);

    List<Dislike> getAll();

    List<Dislike> getAllByUser(User user);

    boolean contains(User user);

    boolean containsById(int id);
}
