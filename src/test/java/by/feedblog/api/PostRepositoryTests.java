package by.feedblog.api;

import by.feedblog.api.dao.repository.CategoryRepository;
import by.feedblog.api.dao.repository.PostRepository;
import by.feedblog.api.dao.repository.UserRepository;
import by.feedblog.api.entity.Category;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Tag;
import by.feedblog.api.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class PostRepositoryTests {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        postRepository.save(new Post("test", "test", categoryRepository.save(new Category("test")),
                userRepository.save(new User("test", "test", "test", 12))));
    }

    @Test
    @Order(1)
    void createPost(){
        Post post = postRepository.getOne(1);
        assertEquals("test", post.getTitle());
    }

    @Test
    @Order(2)
    void deleteById(){
        postRepository.deleteById(2);
        boolean b = postRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void findById(){
        boolean b = postRepository.existsById(3);
        assertTrue(b);
    }

    @Test
    @Order(4)
    void findByTitle(){
        boolean b = postRepository.existsByTitle("test");
        assertTrue(b);
    }

    @Test
    @Order(5)
    void updateDescription(){
        Post post = postRepository.getOne(5);
        post.setDescription("new");
        postRepository.save(post);
        Post one1 = postRepository.getOne(5);
        assertEquals(post, one1);
    }

    @Test
    @Order(6)
    void updateCategory(){
        Post one = postRepository.getOne(6);
        one.setCategory(categoryRepository.save(new Category("new")));
        postRepository.save(one);
        Post one1 = postRepository.getOne(6);
        assertEquals(one, one1);
    }

}
