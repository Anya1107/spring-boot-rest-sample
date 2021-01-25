package by.feedblog.api.service;


import by.feedblog.api.entity.Tag;
import by.feedblog.api.repository.TagDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagDao tagDao;

    public TagService(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    public boolean save(Tag tag){
        if(tagDao.containsByName(tag.getName())){
            throw new IsExistException("Tag is exist!", tag.getName(), "save");
        } else {
            tagDao.save(tag);
        }
        return true;
    }

    public void deleteById(long id){
        if(tagDao.containsById(id)) tagDao.delete(id);
        throw new NotFoundException("Tag with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByName(String name){
        if(tagDao.containsByName(name)) tagDao.delete(name);
        throw new NotFoundException("Tag with name not found!", name, "deleteByName");
    }

    public List<Tag> getALl(){
        return tagDao.getAll();
    }

    public Tag getById(long id){
        if(tagDao.containsById(id)){
            return tagDao.getById(id);
        }
        throw new NotFoundException("Tag with id not found!", String.valueOf(id), "getById");
    }

    public Tag getByName(String name){
        if(tagDao.containsByName(name)){
            return tagDao.getByName(name);
        }
        throw new NotFoundException("Tag with name not found!", name, "getByName");
    }
}
