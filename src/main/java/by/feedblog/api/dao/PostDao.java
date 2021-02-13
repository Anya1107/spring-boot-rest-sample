package by.feedblog.api.dao;

import by.feedblog.api.entity.*;

import java.util.List;

public interface PostDao {
    void save(Post post);

    void deleteById(int id);

    void deleteByTitle(String title);

    List<Post> getAll();

    Post getById(int id);

    Post getByTitle(String title);

    List<Post> getAllByUser(User user);

    List<Post> getAllByTag(Tag tag);

    List<Post> getAllByCategory(Category category);

    boolean containsById(int id);

    boolean containsByTitle(String title);

    void updateDescription(int id, String description);

    void updateTag(int id, Tag tag);

    void updateCategory(int id, Category category);
}
