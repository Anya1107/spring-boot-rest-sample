package by.feedblog.api;

import by.feedblog.api.dao.repository.TagRepository;
import by.feedblog.api.entity.Tag;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class TagRepositoryTests {

    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void init(){
        tagRepository.save(new Tag("test"));
    }

    @Test
    @Order(1)
    void createTag() {
        Tag byId = tagRepository.getOne(1L);
        assertEquals("test", byId.getName());
    }

    @Test
    @Order(2)
    void deleteById(){
        tagRepository.deleteById(2L);
        boolean b = tagRepository.existsById(2L);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void deleteByName(){
        tagRepository.deleteByName("test");
        boolean b = tagRepository.existsByName("test");
        assertFalse(b);
    }

    @Test
    @Order(4)
    void findById(){
        boolean b = tagRepository.existsById(4L);
        assertTrue(b);
    }

    @Test
    @Order(5)
    void findByName(){
        boolean b = tagRepository.existsByName("test");
        assertTrue(b);
    }

}