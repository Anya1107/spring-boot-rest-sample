package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    boolean existsByPost(Post post);
    List<Bookmark> findAllByUser(User user);
}
