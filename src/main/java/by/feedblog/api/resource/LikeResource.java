package by.feedblog.api.resource;

import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.LikeService;
import by.feedblog.api.service.PostService;
import by.feedblog.api.service.UserService;
import by.feedblog.api.service.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/like")
public class LikeResource {
    private LikeService likeService;
    private PostService postService;
    private UserService userService;

    public LikeResource(LikeService likeService, PostService postService, UserService userService) {
        this.likeService = likeService;
        this.postService = postService;
        this.userService = userService;
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        Like byId = likeService.getById(id);
        if(byId != null){
            likeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        Like byId = likeService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Like> all = likeService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllByUser")
    public ResponseEntity<?> getALlByUser(@RequestParam int id){
        if(id < 1) throw new InvalidIdException("Invalid id", id, "getAllByUser");
        User byId = userService.getById(id);
        if (byId != null) {
            List<Like> allByUser = likeService.getAllByUser(byId);
            return new ResponseEntity<>(allByUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
