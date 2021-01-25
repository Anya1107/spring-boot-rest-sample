package by.feedblog.api.resource;

import by.feedblog.api.entity.Category;
import by.feedblog.api.service.CategoryService;
import by.feedblog.api.service.exception.InvalidIdException;
import by.feedblog.api.service.exception.InvalidTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryResource {
    private CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> save(@Valid @RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> delete(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        if(categoryService.getById(id) != null){
            categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/deleteByTitle")
    public ResponseEntity<?> delete(@RequestParam String title){
        if(title.length() < 1 || title.isBlank()){
            throw new InvalidTitleException("Invalid title", title, "deleteByTitle");
        }
        if(categoryService.getByTitle(title) != null){
            categoryService.deleteByTitle(title);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getById");
        }
        Category byId = categoryService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByTitle")
    public ResponseEntity<?> getByTitle(@RequestParam String title){
        if(title.length() < 1 || title.isBlank()){
            throw new InvalidTitleException("Invalid title", title, "getByTitle");
        }
        Category byTitle = categoryService.getByTitle(title);
        if(byTitle != null) return new ResponseEntity<>(byTitle, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Category> all = categoryService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
