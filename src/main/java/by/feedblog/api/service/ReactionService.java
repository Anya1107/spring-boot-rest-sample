package by.feedblog.api.service;


import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Reaction;
import by.feedblog.api.entity.User;
import by.feedblog.api.repository.ReactionDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {
    private ReactionDao reactionDao;

    public ReactionService(ReactionDao reactionDao) {
        this.reactionDao = reactionDao;
    }

    public void add(Reaction reaction){
        if(reactionDao.containsByUser(reaction.getUser())){
            throw new IsExistException("Reaction from this user is exist!", reaction.getUser().getUsername(), "save");
        }
        reactionDao.add(reaction);
    }

    public void deleteById(int id){
        if(reactionDao.containsById(id)) reactionDao.deleteById(id);
        throw new NotFoundException("Reaction is not found!", String.valueOf(id), "deleteById");
    }

    public List<Reaction> getAllByUser(User user){
        return reactionDao.getAllByUser(user);
    }

    public Reaction getById(int id){
        if(reactionDao.containsById(id)) return reactionDao.getById(id);
        throw new NotFoundException("Reaction is not found!", String.valueOf(id), "getById");
    }

    public List<Reaction> getAllByPost(Post post){
        return reactionDao.getAllByPost(post);
    }

    public List<Reaction> getALl(){
        return reactionDao.getAll();
    }

}
