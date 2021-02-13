package by.feedblog.api.service;


import by.feedblog.api.dao.repository.ReactionRepository;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Reaction;
import by.feedblog.api.entity.User;
import by.feedblog.api.dao.ReactionDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReactionService {
    private ReactionRepository reactionRepository;

    public void add(Reaction reaction){
//        if(reactionRepository.existsByUser(reaction.getUser())){
//            throw new IsExistException("Reaction with user is exist!", reaction.getReaction(), "save");
//        }
        reactionRepository.save(reaction);
    }

    public void deleteById(int id){
        if(reactionRepository.existsById(id)) {
            reactionRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Reaction is not found!", String.valueOf(id), "deleteById");
    }

    public List<Reaction> getAllByUser(User user){
        return reactionRepository.findAllByUser(user);
    }

    public Reaction getById(int id){
        if(reactionRepository.existsById(id)) return reactionRepository.getOne(id);
        throw new NotFoundException("Reaction is not found!", String.valueOf(id), "getById");
    }

    public List<Reaction> getALl(){
        return reactionRepository.findAll();
    }

}
