package by.feedblog.api;

import by.feedblog.api.dao.repository.CategoryRepository;
import by.feedblog.api.entity.Category;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void init(){
        categoryRepository.save(new Category("test"));
    }

    @Test
    @Order(1)
    void createCategory(){
        Category category = categoryRepository.getOne(1);
        assertEquals("test", category.getTitle());
    }

    @Test
    @Order(2)
    void deleteById(){
        categoryRepository.deleteById(2);
        boolean b = categoryRepository.existsById(2);
        assertFalse(b);
    }

    @Test
    @Order(3)
    void deleteByTitle(){
        categoryRepository.deleteByTitle("test");
        boolean test = categoryRepository.existsByTitle("test");
        assertFalse(test);
    }

    @Test
    @Order(4)
    void findById(){
        boolean b = categoryRepository.existsById(4);
        assertTrue(b);
    }

    @Test
    @Order(5)
    void findByTitle(){
        boolean test = categoryRepository.existsByTitle("test");
        assertTrue(test);
    }
}
