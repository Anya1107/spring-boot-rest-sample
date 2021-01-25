package by.feedblog.api.service;


import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.repository.DislikeDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DislikeService {

    @Autowired
    private DislikeDao dislikeDao;

    public DislikeService(DislikeDao dislikeDao) {
        this.dislikeDao = dislikeDao;
    }

    public boolean add(Dislike dislike){
        if (dislikeDao.contains(dislike.getUser(), dislike.getPost())) {
            throw new IsExistException("Dislike is exist!", dislike.getPost().getTitle(), "save");
        } else {
            dislikeDao.add(dislike);
            return true;
        }
    }

    public void deleteById(int id){
        if(dislikeDao.containsById(id)) dislikeDao.deleteById(id);
        throw new NotFoundException("Dislike with id not found!", String.valueOf(id), "deleteById");
    }

    public Dislike getById(int id){
        if(dislikeDao.containsById(id)) return dislikeDao.getById(id);
        throw new NotFoundException("Dislike with id not found!", String.valueOf(id), "getById");
    }

    public List<Dislike> getAll(){
        return dislikeDao.getAll();
    }

    public List<Dislike> getAllByUser(User user){
        return dislikeDao.getAllByUser(user);
    }

    public List<Dislike> getAllByPost(Post post){
        return dislikeDao.getAllByPost(post);
    }
}
