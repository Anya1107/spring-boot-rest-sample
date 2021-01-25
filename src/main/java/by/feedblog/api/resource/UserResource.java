package by.feedblog.api.resource;

import by.feedblog.api.entity.User;
import by.feedblog.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserResource {
    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> save(@Valid @RequestBody User user){
        if (userService.registration(user)) return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> delete(@RequestParam int id){
        if(userService.getById(id) != null){
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/deleteByName")
    public ResponseEntity<?> delete(@RequestParam String username){
        if(userService.getByName(username) != null){
            userService.deleteByName(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getById")
    public ResponseEntity<User> getById(@RequestParam int id){
        User byId = userService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByName")
    public ResponseEntity<User> getByName(@RequestParam String username){
        User byName = userService.getByName(username);
        if(byName != null) return new ResponseEntity<>(byName, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<User>> getAll(){
        List<User> all = userService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<?> update(@RequestParam int id, @Valid @RequestBody User user){
        User byId = userService.getById(id);
        if(byId != null){
            userService.updateName(id, user.getUsername());
            userService.updatePassword(id, user.getPassword());
            userService.updateAge(id, user.getAge());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/authorization")
    public ResponseEntity<String> authorization(@RequestParam String login, @RequestParam String password){
        String uuid = userService.authorization(login, password);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }
}
