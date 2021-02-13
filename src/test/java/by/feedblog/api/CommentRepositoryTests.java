package by.feedblog.api;

import by.feedblog.api.dao.repository.CommentRepository;
import by.feedblog.api.entity.Comment;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class CommentRepositoryTests {
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void init(){
        commentRepository.save(new Comment("test"));
    }

    @Test
    @Order(1)
    void createComment(){
        Comment comment = commentRepository.getOne(1);
        assertEquals("test", comment.getComment());
    }

    @Test
    @Order(2)
    void deleteById(){
        commentRepository.deleteById(2);
        boolean b = commentRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void findById(){
        boolean b = commentRepository.existsById(3);
        assertTrue(b);
    }
}
