package by.feedblog.api.service;


import by.feedblog.api.dao.repository.TagRepository;
import by.feedblog.api.entity.Tag;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TagService {

    private TagRepository tagRepository;

    public void deleteById(long id){
        if(tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Tag with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByName(String name){
        if(tagRepository.existsByName(name)){
            tagRepository.deleteByName(name);
            return;
        }
        throw new NotFoundException("Tag with name not found!", name, "deleteByName");
    }

    public List<Tag> getALl(){
        return tagRepository.findAll();
    }

    public Tag getById(long id){
        if(tagRepository.existsById(id)){
            return tagRepository.findById(id).get();
        }
        throw new NotFoundException("Tag with id not found!", String.valueOf(id), "getById");
    }

    public Tag getByName(String name){
        if(tagRepository.existsByName(name)){
            return tagRepository.findByName(name);
        }
        throw new NotFoundException("Tag with name not found!", name, "getByName");
    }
}
