package by.feedblog.api.service;


import by.feedblog.api.dao.repository.CommentRepository;
import by.feedblog.api.entity.Comment;
import by.feedblog.api.entity.User;
import by.feedblog.api.dao.CommentDao;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CommentService {
    private CommentRepository commentRepository;

    public void deleteById(int id){
        if(commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Comment with id not found!", String.valueOf(id), "deleteById");
    }

    public Comment getById(int id){
        if(commentRepository.existsById(id)) return commentRepository.findById(id).get();
        throw new NotFoundException("Comment with id not found!", String.valueOf(id), "getById");
    }

    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

}
