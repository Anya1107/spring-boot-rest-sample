package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Tag;
import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
}
