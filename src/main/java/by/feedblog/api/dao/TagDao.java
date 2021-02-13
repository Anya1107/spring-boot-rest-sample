package by.feedblog.api.dao;



import by.feedblog.api.entity.Tag;

import java.util.List;

public interface TagDao {
    void save(Tag tag);

    void delete(long id);

    void delete(String name);

    List<Tag> getAll();

    Tag getById(long id);

    Tag getByName(String name);

    boolean containsById(long id);

    boolean containsByName(String name);
}
