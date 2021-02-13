package by.feedblog.api.resource;

import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.DislikeService;
import by.feedblog.api.service.PostService;
import by.feedblog.api.service.UserService;
import by.feedblog.api.service.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/dislike")
public class DislikeResource {
    private DislikeService dislikeService;
    private PostService postService;
    private UserService userService;

    public DislikeResource(DislikeService dislikeService, PostService postService, UserService userService) {
        this.dislikeService = dislikeService;
        this.postService = postService;
        this.userService = userService;
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        Dislike byId = dislikeService.getById(id);
        if(byId != null){
            dislikeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        Dislike byId = dislikeService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Dislike> all = dislikeService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllByUser")
    public ResponseEntity<?> getALlByUser(@RequestParam int id){
        if(id < 1) throw new InvalidIdException("Invalid id", id, "getAllByUser");
        User byId = userService.getById(id);
        if (byId != null) {
            List<Dislike> allByUser = dislikeService.getAllByUser(byId);
            return new ResponseEntity<>(allByUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
