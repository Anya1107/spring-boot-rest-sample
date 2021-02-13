package by.feedblog.api;

import by.feedblog.api.dao.repository.CategoryRepository;
import by.feedblog.api.dao.repository.DislikeRepository;
import by.feedblog.api.dao.repository.PostRepository;
import by.feedblog.api.dao.repository.UserRepository;
import by.feedblog.api.entity.Category;
import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class DislikeRepositoryTests {
    @Autowired
    private DislikeRepository dislikeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void init(){
        dislikeRepository.save(new Dislike(userRepository.save(new User("test", "test", "test", 12)),
                new Post("test", "test", categoryRepository.save(new Category("test")), userRepository.save(new User("test", "test", "test", 12)))));
    }

    @Test
    @Order(1)
    void createDislike(){
        Dislike dislike = dislikeRepository.getOne(1);
        assertEquals("test", dislike.getPost().getTitle());
    }

    @Test
    @Order(2)
    void deleteById(){
        dislikeRepository.deleteById(2);
        boolean b = dislikeRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void findById(){
        boolean b = dislikeRepository.existsById(3);
        assertTrue(b);
    }
}
