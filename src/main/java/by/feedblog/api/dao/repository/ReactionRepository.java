package by.feedblog.api.dao.repository;

import by.feedblog.api.entity.Reaction;
import by.feedblog.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Integer> {
    boolean existsByUser(User user);
    List<Reaction> findAllByUser(User user);
}
