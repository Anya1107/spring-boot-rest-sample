package by.feedblog.api.service;


import by.feedblog.api.entity.Category;
import by.feedblog.api.repository.CategoryDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public boolean save(Category category){
        if(categoryDao.containsByTitle(category.getTitle())){
            throw new IsExistException("Category is exist!", category.getTitle(), "save");
        } else {
            categoryDao.save(category);
        }
        return true;
    }

    public void deleteById(int id){
        if(categoryDao.containsById(id)) categoryDao.deleteById(id);
        throw new NotFoundException("Category with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByTitle(String title){
        if(categoryDao.containsByTitle(title)) categoryDao.deleteByTitle(title);
        throw new NotFoundException("Category with title not found!", title, "deleteByTitle");
    }

    public List<Category> getAll(){
        return categoryDao.getAll();
    }

    public Category getById(int id){
        if(categoryDao.containsById(id)) return categoryDao.getById(id);
        throw new NotFoundException("Category with id not found!", String.valueOf(id), "getById");
    }

    public Category getByTitle(String title){
        if(categoryDao.containsByTitle(title)) return categoryDao.getByTitle(title);
        throw new NotFoundException("Category with title not found!", title, "deleteByTitle");
    }
}
