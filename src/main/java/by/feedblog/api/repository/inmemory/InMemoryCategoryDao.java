package by.feedblog.api.repository.inmemory;

import by.feedblog.api.entity.Category;
import by.feedblog.api.repository.CategoryDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryCategoryDao implements CategoryDao {
    private final List<Category> categories = new ArrayList<>();
    private static int incId = 1;


    @Override
    public void save(Category category) {
        category.setId(incId++);
        categories.add(category);
    }

    @Override
    public void deleteById(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                categories.remove(category);
                break;
            }
        }
    }

    @Override
    public void deleteByTitle(String title) {
        for (Category category : categories) {
            if (category.getTitle().equals(title)) {
                categories.remove(category);
                break;
            }
        }
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category getById(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    @Override
    public Category getByTitle(String title) {
        for (Category category : categories) {
            if (category.getTitle().equals(title)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public boolean containsById(int id) {
        for (Category category : categories) {
            if (category.getId() == id) return true;
        }
        return false;
    }

    @Override
    public boolean containsByTitle(String title) {
        for (Category category : categories) {
            if (category.getTitle().equals(title)) return true;
        }
        return false;
    }
}
