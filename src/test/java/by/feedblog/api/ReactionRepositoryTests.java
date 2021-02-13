package by.feedblog.api;

import by.feedblog.api.dao.repository.ReactionRepository;
import by.feedblog.api.dao.repository.UserRepository;
import by.feedblog.api.entity.Reaction;
import by.feedblog.api.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ReactionRepositoryTests {
    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        reactionRepository.save(new Reaction("test", userRepository.save(new User("test", "test", "test", 12))));
    }

    @Test
    @Order(1)
    void createReaction(){
        Reaction reaction = reactionRepository.getOne(1);
        assertEquals("test", reaction.getReaction());
    }

    @Test
    @Order(2)
    void deleteById(){
        reactionRepository.deleteById(2);
        boolean b = reactionRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void findById(){
        boolean b = reactionRepository.existsById(3);
        assertTrue(b);
    }
}
