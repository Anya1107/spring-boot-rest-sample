package by.feedblog.api.repository;

import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;

import java.util.List;


public interface BookmarkDao {
    void add(Bookmark bookmark);

    void deleteById(int id);

    Bookmark getById(int id);

    List<Bookmark> getAll();

    List<Bookmark> getAllByUser(User user);

    boolean containsByPost(Post post);

    boolean containsById(int id);

}
