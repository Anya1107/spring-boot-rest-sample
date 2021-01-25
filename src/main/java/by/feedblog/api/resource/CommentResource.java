package by.feedblog.api.resource;

import by.feedblog.api.entity.Comment;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.CommentService;
import by.feedblog.api.service.PostService;
import by.feedblog.api.service.UserService;
import by.feedblog.api.service.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/comment")
public class CommentResource {
    private CommentService commentService;
    private PostService postService;
    private UserService userService;

    public CommentResource(CommentService commentService, PostService postService, UserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> save(@Valid @RequestBody Comment comment){
        comment.setPost(postService.getById(comment.getPost().getId()));
        comment.setUser(userService.getById(comment.getUser().getId()));
        commentService.save(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "deleteById");
        }
        if(commentService.getById(id) != null){
            commentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        if(id < 1){
            throw new InvalidIdException("Invalid id", id, "getById");
        }
        Comment byId = commentService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Comment> all = commentService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllByPostId")
    public ResponseEntity<?> getAllByPost(@RequestParam int postId){
        if(postId < 1){
            throw new InvalidIdException("Invalid id", postId, "getAllByPost");
        }
        Post byId = postService.getById(postId);
        if(byId != null){
            List<Comment> allByPost = commentService.getAllByPostId(postId);
            return new ResponseEntity<>(allByPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAllByUser")
    public ResponseEntity<?> getAllByUser(@RequestParam int userId){
        if(userId < 1){
            throw new InvalidIdException("Invalid id", userId, "getById");
        }
        User byId = userService.getById(userId);
        if(byId != null){
            List<Comment> allByUser = commentService.getAllByUser(byId);
            return new ResponseEntity<>(allByUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
