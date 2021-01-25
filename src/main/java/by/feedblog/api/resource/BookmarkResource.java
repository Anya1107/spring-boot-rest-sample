package by.feedblog.api.resource;

import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.BookmarkService;
import by.feedblog.api.service.PostService;
import by.feedblog.api.service.UserService;
import by.feedblog.api.service.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/bookmark")
public class BookmarkResource {
    private BookmarkService bookmarkService;
    private PostService postService;
    private UserService userService;

    public BookmarkResource(BookmarkService bookmarkService, PostService postService, UserService userService) {
        this.bookmarkService = bookmarkService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> save(@RequestParam int userId, @RequestParam int postId){
        if(postId < 1) throw new InvalidIdException("Invalid id", postId, "save");
        if(userId < 1) throw new InvalidIdException("Invalid id", userId, "save");
        User user = userService.getById(userId);
        Post post = postService.getById(postId);
        if(user != null && post != null){
            bookmarkService.add(new Bookmark(user, post));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        Bookmark byId = bookmarkService.getById(id);
        if(byId != null){
            bookmarkService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getById");
        }
        Bookmark byId = bookmarkService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Bookmark> all = bookmarkService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllByUser")
    public ResponseEntity<?> getAllByUser(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getAllByUser");
        }
        User byId = userService.getById(id);
        if(byId != null){
            List<Bookmark> allByUser = bookmarkService.getAllByUser(byId);
            return new ResponseEntity<>(allByUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
