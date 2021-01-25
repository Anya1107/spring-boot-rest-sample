package by.feedblog.api.resource;

import by.feedblog.api.entity.Category;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Tag;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.*;
import by.feedblog.api.service.exception.InvalidIdException;
import by.feedblog.api.service.exception.InvalidTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class PostResource {
    private PostService postService;
    private TagService tagService;
    private CategoryService categoryService;
    private UserService userService;
    private CommentService commentService;

    public PostResource(PostService postService, TagService tagService, CategoryService categoryService,
                        UserService userService, CommentService commentService) {
        this.postService = postService;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Post post){
        post.setTag(tagService.getById(post.getTag().getId()));
        post.setCategory(categoryService.getById(post.getCategory().getId()));
        post.setUser(userService.getById(post.getUser().getId()));
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        if(postService.getById(id) != null){
            postService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/deleteByTitle")
    public ResponseEntity<?> deleteByName(@RequestParam String title){
        if(title.length() < 1 || title.isBlank()){
            throw new InvalidTitleException("Invalid title", title, "deleteByTitle");
        }
        if(postService.getByTitle(title) != null){
            postService.deleteByTitle(title);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getById");
        }
        Post byId = postService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByTitle")
    public ResponseEntity<?> getByTitle(@RequestParam String title){
        if(title.length() < 1 || title.isBlank()){
            throw new InvalidTitleException("Invalid title", title, "getByTitle");
        }
        Post byTitle = postService.getByTitle(title);
        if(byTitle != null) return new ResponseEntity<>(byTitle, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getALl(){
        List<Post> all = postService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllByUser")
    public ResponseEntity<?> getAllByUser(@RequestParam int userId){
        if(userId < 1){
            throw new InvalidIdException("Invalid id", userId, "getAllByUser");
        }
        User byId = userService.getById(userId);
        if(byId != null){
            List<Post> allByUser = postService.getAllByUser(byId);
            return new ResponseEntity<>(allByUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAllByTag")
    public ResponseEntity<?> getAllByTag(@RequestParam long tagId){

        Tag byId = tagService.getById(tagId);
        if(byId != null){
            List<Post> allByTag = postService.getAllByTag(byId);
            return new ResponseEntity<>(allByTag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAllByCategory")
    public ResponseEntity<?> getALlByCategory(@RequestParam int categoryId){
        if(categoryId < 1){
            throw new InvalidIdException("Invalid id", categoryId, "getAllByCategory");
        }
        Category byId = categoryService.getById(categoryId);
        if(byId != null){
            List<Post> allByCategory = postService.getAllByCategory(byId);
            return new ResponseEntity<>(allByCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAllChecked")
    public ResponseEntity<?> getAllChecked(){
        List<Post> allCheckedPosts = postService.getAllCheckedPosts();
        return new ResponseEntity<>(allCheckedPosts, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllUnchecked")
    public ResponseEntity<?> getAllUnchecked(){
        List<Post> allUncheckedPosts = postService.getAllUncheckedPosts();
        return new ResponseEntity<>(allUncheckedPosts, HttpStatus.OK);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<?> update(@RequestParam int id, @RequestBody Post post){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "update");
        }
        Post byId = postService.getById(id);
        if(byId != null){
            postService.updateDescription(id, post.getDescription());
            postService.updateTag(id, post.getTag());
            postService.updateCategory(id, post.getCategory());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
