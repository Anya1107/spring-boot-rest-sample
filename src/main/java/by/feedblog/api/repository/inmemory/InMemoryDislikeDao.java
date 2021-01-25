package by.feedblog.api.repository.inmemory;

import by.feedblog.api.entity.Dislike;
import by.feedblog.api.entity.Like;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.repository.DislikeDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryDislikeDao implements DislikeDao {
    private List<Dislike> dislikes = new ArrayList<>();
    private static int incId = 1;

    @Override
    public void add(Dislike dislike) {
        dislike.setId(incId++);
        dislikes.add(dislike);
    }

    @Override
    public void deleteById(int id) {
        for (Dislike dislike : dislikes) {
            if(dislike.getId() == id){
                dislikes.remove(dislike);
                break;
            }
        }
    }

    @Override
    public Dislike getById(int id) {
        for (Dislike dislike : dislikes) {
            if(dislike.getId() == id) return dislike;
        }
        return null;
    }

    @Override
    public List<Dislike> getAll() {
        return dislikes;
    }

    @Override
    public List<Dislike> getAllByUser(User user) {
        List<Dislike> dislikeList = new ArrayList<>();
        for (Dislike dislike : dislikes) {
            if(dislike.getUser().equals(user)){
                dislikeList.add(dislike);
            }
        }
        return dislikeList;
    }

    @Override
    public List<Dislike> getAllByPost(Post post) {
        List<Dislike> dislikeList = new ArrayList<>();
        for (Dislike dislike : dislikes) {
            if(dislike.getPost().equals(post)){
                dislikeList.add(dislike);
            }
        }
        return dislikeList;
    }

    @Override
    public boolean contains(User user, Post post) {
        for (Dislike dislike : dislikes) {
            if(dislike.getPost().equals(post) && dislike.getUser().equals(user)) return true;
        }
        return false;
    }

    @Override
    public boolean containsById(int id) {
        for (Dislike dislike : dislikes) {
            if(dislike.getId() == id) return true;
        }
        return false;
    }
}
