package by.feedblog.api.service;


import by.feedblog.api.entity.*;
import by.feedblog.api.repository.CommentDao;
import by.feedblog.api.repository.DislikeDao;
import by.feedblog.api.repository.LikeDao;
import by.feedblog.api.repository.PostDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostDao postDao;
    private CommentDao commentDao;
    private LikeDao likeDao;
    private DislikeDao dislikeDao;

    public PostService(PostDao postDao, CommentDao commentDao, LikeDao likeDao, DislikeDao dislikeDao) {
        this.postDao = postDao;
        this.commentDao = commentDao;
        this.likeDao = likeDao;
        this.dislikeDao = dislikeDao;
    }

    public boolean save(Post post){
        if(postDao.containsByTitle(post.getTitle())){
            throw new IsExistException("Post is exist!", post.getTitle(), "save");
        } else {
            postDao.save(post);
        }
        return true;
    }

    public void deleteById(int id){
        if(postDao.containsById(id)){
            postDao.deleteById(id);
        }
        throw new NotFoundException("Post with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByTitle(String title){
        if(postDao.containsByTitle(title)){
            postDao.deleteByTitle(title);
        }
        throw new NotFoundException("Post with title not found!", title, "deleteByTitle");
    }

    public List<Post> getAll(){
        return postDao.getAll();
    }

    public Post getById(int id){
        if(postDao.containsById(id)){
            Post byId = postDao.getById(id);
            byId.setComments(commentDao.getAllByPostId(id));
            byId.setLikes(likeDao.getAllByPost(byId));
            byId.setDislikes(dislikeDao.getAllByPost(byId));
            return byId;
        }
        throw new NotFoundException("Post with id not found!", String.valueOf(id), "getById");
    }

    public Post getByTitle(String title){
        if(postDao.containsByTitle(title)){
            return postDao.getByTitle(title);
        }
        throw new NotFoundException("Post with title not found!", title, "getByTitle");
    }

    public List<Post> getAllCheckedPosts(){
        return postDao.getAllChecked();
    }

    public List<Post> getAllUncheckedPosts(){
        return postDao.getAllUnchecked();
    }

    public List<Post> getAllByUser(User user){
        return postDao.getAllByUser(user);
    }

    public List<Post> getAllByTag(Tag tag){
        return postDao.getAllByTag(tag);
    }

    public List<Post> getAllByCategory(Category category){
        return postDao.getAllByCategory(category);
    }

    public void updateDescription(int id, String description){
        if(!postDao.containsById(id)) throw new NotFoundException("Post with id not found!", String.valueOf(id), "update");
        postDao.updateDescription(id, description);
    }

    public void updateTag(int id, Tag tag){
        if(!postDao.containsById(id)) throw new NotFoundException("Post with id not found!", String.valueOf(id), "update");
        postDao.updateTag(id, tag);
    }

    public void updateCategory(int id, Category category){
        if(!postDao.containsById(id)) throw new NotFoundException("Post with id not found!", String.valueOf(id), "update");
        postDao.updateCategory(id, category);
    }
}
