package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Category;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Tag;
import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    boolean existsByTitle(String title);
    void deleteByTitle(String title);
    Post findByTitle(String title);
    List<Post> findAllByUser(User user);
    List<Post> findAllByTag(Tag tag);
    List<Post> findAllByCategory(Category category);
}
