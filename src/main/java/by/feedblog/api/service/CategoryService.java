package by.feedblog.api.service;


import by.feedblog.api.dao.repository.CategoryRepository;
import by.feedblog.api.entity.Category;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    public boolean save(Category category){
        if(categoryRepository.existsByTitle(category.getTitle())){
            throw new IsExistException("Category is exist!", category.getTitle(), "save");
        } else {
            categoryRepository.save(category);
        }
        return true;
    }

    public void deleteById(int id){
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Category with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByTitle(String title){
        if(categoryRepository.existsByTitle(title)){
            categoryRepository.deleteByTitle(title);
            return;
        }
        throw new NotFoundException("Category with title not found!", title, "deleteByTitle");
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category getById(int id){
        if(categoryRepository.existsById(id)) return categoryRepository.findById(id).get();
        throw new NotFoundException("Category with id not found!", String.valueOf(id), "getById");
    }

    public Category getByTitle(String title){
        if(categoryRepository.existsByTitle(title)) return categoryRepository.findByTitle(title);
        throw new NotFoundException("Category with title not found!", title, "deleteByTitle");
    }
}
