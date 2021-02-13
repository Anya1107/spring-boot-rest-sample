package by.feedblog.api.service;


import by.feedblog.api.dao.repository.LikeRepository;
import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeService {

    private LikeRepository likeRepository;

    public void deleteById(int id){
        if(likeRepository.existsById(id)){
            likeRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Like with id not found!", String.valueOf(id), "deleteById");
    }

    public Like getById(int id){
        if(likeRepository.existsById(id)) return likeRepository.findById(id).get();
        throw new NotFoundException("Like with id not found!", String.valueOf(id), "getById");
    }

    public List<Like> getAll(){
        return likeRepository.findAll();
    }

    public List<Like> getAllByUser(User user){
        return likeRepository.findAllByUser(user);
    }
}
