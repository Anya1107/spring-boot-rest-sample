package by.feedblog.api.dao.inmemory;

import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.dao.LikeDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class InMemoryLikeDao implements LikeDao {
    private List<Like> likes = new ArrayList<>();
    private static int incId = 1;

    @Override
    public void deleteById(int id) {
        for (Like like : likes) {
            if(like.getId() == id){
                likes.remove(like);
                break;
            }
        }
    }

    @Override
    public Like getById(int id) {
        for (Like like : likes) {
            if(like.getId() == id) return like;
        }
        return null;
    }

    @Override
    public List<Like> getAll() {
        return likes;
    }

    @Override
    public List<Like> getAllByUser(User user) {
        List<Like> likeList = new ArrayList<>();
        for (Like like : likes) {
            if(like.getUser().equals(user)){
                likeList.add(like);
            }
        }
        return likeList;
    }

    @Override
    public boolean contains(User user) {
        for (Like like : likes) {
            if( like.getUser().equals(user)) return true;
        }
        return false;
    }

    @Override
    public boolean containsById(int id) {
        for (Like like : likes) {
            if(like.getId() == id) return true;
        }
        return false;
    }
}
