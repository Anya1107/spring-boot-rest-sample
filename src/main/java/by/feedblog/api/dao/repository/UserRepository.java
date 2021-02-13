package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByFullName(String name);
    void deleteByFullName(String name);
    User findByFullName(String name);
    boolean existsByFullNameAndPassword(String name, String password);
}
