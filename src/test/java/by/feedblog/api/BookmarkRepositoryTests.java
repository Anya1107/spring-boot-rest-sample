package by.feedblog.api;

import by.feedblog.api.dao.repository.BookmarkRepository;
import by.feedblog.api.dao.repository.CategoryRepository;
import by.feedblog.api.dao.repository.PostRepository;
import by.feedblog.api.dao.repository.UserRepository;
import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.Category;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class BookmarkRepositoryTests {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void init(){
        bookmarkRepository.save(new Bookmark(userRepository.save(new User("test", "test", "test", 12)),
                new Post("test", "test", categoryRepository.save(new Category("test")), new User("test","test",
                        "test", 12))));
    }

    @Test
    @Order(1)
    void createBookmark(){
        Bookmark bookmark = bookmarkRepository.getOne(1);
        assertEquals("test", bookmark.getPost().getTitle());
    }

    @Test
    @Order(2)
    void deleteById(){
        bookmarkRepository.deleteById(2);
        boolean b = bookmarkRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void findById(){
        boolean b = bookmarkRepository.existsById(3);
        assertTrue(b);
    }
}
