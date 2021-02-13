package by.feedblog.api.dao.inmemory;

import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Reaction;
import by.feedblog.api.entity.User;
import by.feedblog.api.dao.ReactionDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class InMemoryReactionDao implements ReactionDao {
    private List<Reaction> reactions = new ArrayList<>();
    private static int incId  = 1;

    @Override
    public void add(Reaction reaction) {
        reaction.setId(incId++);
        reactions.add(reaction);
    }

    @Override
    public void deleteById(int id) {
        for (Reaction reaction : reactions) {
            if(reaction.getId() == id){
                reactions.remove(reaction);
                break;
            }
        }
    }

    @Override
    public Reaction getById(int id) {
        for (Reaction reaction : reactions) {
            if(reaction.getId() == id) return reaction;
        }
        return null;
    }


    @Override
    public List<Reaction> getAllByUser(User user) {
        List<Reaction> reactionList = new ArrayList<>();
        for (Reaction reaction : reactions) {
            if(reaction.getUser().equals(user)){
                reactionList.add(reaction);
            }
        }
        return reactionList;
    }

    @Override
    public List<Reaction> getAll() {
        return reactions;
    }

    @Override
    public boolean containsByUser(User user) {
        for (Reaction reaction : reactions) {
            if(reaction.getUser().equals(user)) return true;
        }
        return false;
    }

    @Override
    public boolean containsById(int id) {
        for (Reaction reaction : reactions) {
            if(reaction.getId() == id) return true;
        }
        return false;
    }
}
