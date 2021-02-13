package by.feedblog.api.dao;


import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Reaction;
import by.feedblog.api.entity.User;

import java.util.List;

public interface ReactionDao {
    void add(Reaction reaction);

    void deleteById(int id);

    Reaction getById(int id);

    List<Reaction> getAllByUser(User user);

    List<Reaction> getAll();

    boolean containsByUser(User user);

    boolean containsById(int id);
}
