package by.feedblog.api;

import by.feedblog.api.dao.repository.CategoryRepository;
import by.feedblog.api.dao.repository.LikeRepository;
import by.feedblog.api.dao.repository.PostRepository;
import by.feedblog.api.dao.repository.UserRepository;
import by.feedblog.api.entity.Category;
import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class LikeRepositoryTests {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void init(){
        likeRepository.save(new Like(userRepository.save(new User("test", "test", "test", 12)),
                new Post("test", "test", categoryRepository.save(new Category("test")), userRepository.save(new User("test", "test", "test", 12)))));
    }

    @Test
    @Order(1)
    void createLike(){
        Like like = likeRepository.getOne(1);
        assertEquals("test", like.getPost().getTitle());
    }

    @Test
    @Order(2)
    void deleteById(){
        likeRepository.deleteById(2);
        boolean b = likeRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void findById(){
        boolean b = likeRepository.existsById(3);
        assertTrue(b);
    }
}
