package by.feedblog.api.resource;

import by.feedblog.api.entity.Tag;
import by.feedblog.api.service.TagService;
import by.feedblog.api.service.exception.InvalidIdException;
import by.feedblog.api.service.exception.InvalidTitleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tag")
public class TagResource {
    private TagService tagService;

    public TagResource(TagService tagService) {
        this.tagService = tagService;
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam long id){
        if(tagService.getById(id) != null){
            tagService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/deleteByName")
    public ResponseEntity<?> deleteById(@RequestParam String name){
        if(name.length() < 1 || name.isBlank()){
            throw new InvalidTitleException("Invalid name", name, "deleteByName");
        }
        if(tagService.getByName(name) != null){
            tagService.deleteByName(name);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping(path = "/getById")
    public ResponseEntity<?> getById(@RequestParam long id){
        Tag byId = tagService.getById(id);
        if(byId != null) return new ResponseEntity<>(byId, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getByName")
    public ResponseEntity<?> getByName(@RequestParam String name){
        if(name.length() < 1 || name.isBlank()){
            throw new InvalidTitleException("Invalid name", name, "getByName");
        }
        Tag byName = tagService.getByName(name);
        if(byName != null) return new ResponseEntity<>(byName, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<?> getAll(){
        List<Tag> aLl = tagService.getALl();
        return new ResponseEntity<>(aLl, HttpStatus.OK);
    }
}
