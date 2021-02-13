package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
    boolean existsByTitle(String title);
    void deleteByTitle(String title);
    Category findByTitle(String title);
}
