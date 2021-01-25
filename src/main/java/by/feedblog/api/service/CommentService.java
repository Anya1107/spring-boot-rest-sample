package by.feedblog.api.service;


import by.feedblog.api.entity.Comment;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.repository.CommentDao;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentDao commentDao;

    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void save(Comment comment){
        commentDao.save(comment);
    }

    public void deleteById(int id){
        if(commentDao.containsById(id)) commentDao.deleteById(id);
        throw new NotFoundException("Comment with id not found!", String.valueOf(id), "deleteById");
    }

    public Comment getById(int id){
        if(commentDao.containsById(id)) return commentDao.getById(id);
        throw new NotFoundException("Comment with id not found!", String.valueOf(id), "getById");
    }

    public List<Comment> getAll(){
        return commentDao.getAll();
    }

    public List<Comment> getAllByPostId(int id){
        return commentDao.getAllByPostId(id);
    }

    public List<Comment> getAllByUser(User user){
        return commentDao.getAllByUser(user);
    }
}
