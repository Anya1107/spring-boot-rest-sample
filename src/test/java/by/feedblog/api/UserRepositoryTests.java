package by.feedblog.api;

import by.feedblog.api.dao.repository.UserRepository;
import by.feedblog.api.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        userRepository.save(new User("test", "test", "test", 12));
    }

    @Test
    @Order(1)
    void createUser(){
        User user = userRepository.getOne(1);
        assertEquals("test", user.getUsername());
    }

    @Test
    @Order(2)
    void deleteById(){
        userRepository.deleteById(2);
        boolean b = userRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void deleteByName(){
        userRepository.deleteByFullName("test");
        boolean b = userRepository.existsByFullName("test");
        assertFalse(b);
    }

    @Test
    @Order(4)
    void findById(){
        boolean b = userRepository.existsById(4);
        assertTrue(b);
    }

    @Test
    @Order(5)
    void findByName(){
        boolean test = userRepository.existsByFullName("test");
        assertTrue(test);
    }

    @Test
    @Order(6)
    void updateName(){
        User one = userRepository.getOne(6);
        one.setUsername("new");
        userRepository.save(one);
        User one1 = userRepository.getOne(6);
        assertEquals(one, one1);
    }

    @Test
    @Order(7)
    void updatePassword(){
        User one = userRepository.getOne(7);
        one.setPassword("newPas");
        userRepository.save(one);
        User one1 = userRepository.getOne(7);
        assertEquals(one, one1);
    }

}
