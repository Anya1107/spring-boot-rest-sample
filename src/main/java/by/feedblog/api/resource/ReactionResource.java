package by.feedblog.api.resource;

import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Reaction;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.PostService;
import by.feedblog.api.service.ReactionService;
import by.feedblog.api.service.UserService;
import by.feedblog.api.service.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/reaction")
public class ReactionResource {
    private ReactionService reactionService;
    private PostService postService;
    private UserService userService;

    public ReactionResource(ReactionService reactionService, PostService postService, UserService userService) {
        this.reactionService = reactionService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> save(@RequestParam String reaction, @RequestParam int postId, @RequestParam int userId){
        if(postId < 1) throw new InvalidIdException("Invalid id", postId, "save");
        if(userId < 1) throw new InvalidIdException("Invalid id", userId, "save");
        Post post = postService.getById(postId);
        User user = userService.getById(userId);
        if(post != null && user != null){
            reactionService.add(new Reaction(reaction, user, post));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        if(reactionService.getById(id) != null){
            reactionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getById");
        }
        Reaction byId = reactionService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Reaction> aLl = reactionService.getALl();
        return new ResponseEntity<>(aLl, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllByPost")
    public ResponseEntity<?> getAllByPost(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getById");
        }
        Post byId = postService.getById(id);
        if(byId != null){
            List<Reaction> allByPost = reactionService.getAllByPost(byId);
            return new ResponseEntity<>(allByPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAllByUser")
    public ResponseEntity<?> getAllByUser(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getById");
        }
        User byId = userService.getById(id);
        if(byId != null){
            List<Reaction> allByUser = reactionService.getAllByUser(byId);
            return new ResponseEntity<>(allByUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
