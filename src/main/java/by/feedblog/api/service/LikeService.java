package by.feedblog.api.service;


import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.repository.LikeDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeDao likeDao;

    public LikeService(LikeDao likeDao) {
        this.likeDao = likeDao;
    }

    public boolean add(Like like){
        if (likeDao.contains(like.getUser(), like.getPost())) {
            throw new IsExistException("Like is exist!", like.getPost().getTitle(), "save");
        } else {
            likeDao.add(like);
            return true;
        }
    }

    public void deleteById(int id){
        if(likeDao.containsById(id)) likeDao.deleteById(id);
        throw new NotFoundException("Like with id not found!", String.valueOf(id), "deleteById");
    }

    public Like getById(int id){
        if(likeDao.containsById(id)) return likeDao.getById(id);
        throw new NotFoundException("Like with id not found!", String.valueOf(id), "getById");
    }

    public List<Like> getAll(){
        return likeDao.getAll();
    }

    public List<Like> getAllByUser(User user){
        return likeDao.getAllByUser(user);
    }

    public List<Like> getALlByPost(Post post){
        return likeDao.getAllByPost(post);
    }
}
