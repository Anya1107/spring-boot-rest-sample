package by.feedblog.api.service;


import by.feedblog.api.dao.repository.DislikeRepository;
import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DislikeService {

    private DislikeRepository dislikeRepository;

    public void deleteById(int id){
        if(dislikeRepository.existsById(id)){
            dislikeRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Dislike with id not found!", String.valueOf(id), "deleteById");
    }

    public Dislike getById(int id){
        if(dislikeRepository.existsById(id)) return dislikeRepository.findById(id).get();
        throw new NotFoundException("Dislike with id not found!", String.valueOf(id), "getById");
    }

    public List<Dislike> getAll(){
        return dislikeRepository.findAll();
    }

    public List<Dislike> getAllByUser(User user){
        return dislikeRepository.findAllByUser(user);
    }

}
