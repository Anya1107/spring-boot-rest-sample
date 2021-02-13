package by.feedblog.api;

import by.feedblog.api.dao.repository.TagRepository;
import by.feedblog.api.entity.Tag;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
class ApiApplicationTests {

    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    void init(){
        tagRepository.save(new Tag("test"));
    }

    @Test
    void createTag() {
        Tag byId = tagRepository.getOne(1L);
        assertEquals("test", byId.getName());
    }

    @Test
    void deleteById(){
        tagRepository.deleteById(2L);
        boolean b = tagRepository.existsById(2L);
        assertFalse(b);
    }

    @Test
    void deleteByName(){
        tagRepository.deleteByName("test");
        boolean b = tagRepository.existsByName("test");
    }

}
