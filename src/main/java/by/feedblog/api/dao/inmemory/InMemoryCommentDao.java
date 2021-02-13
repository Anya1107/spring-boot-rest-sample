package by.feedblog.api.dao.inmemory;

import by.feedblog.api.entity.Comment;
import by.feedblog.api.entity.User;
import by.feedblog.api.dao.CommentDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryCommentDao implements CommentDao {
    private final List<Comment> comments = new ArrayList<>();
    private static int incId = 1;


    @Override
    public void deleteById(int id) {
        for (Comment comment : comments) {
            if (comment.getId() == id) {
                comments.remove(comment);
                break;
            }
        }
    }

    @Override
    public Comment getById(int id) {
        for (Comment comment : comments) {
            if (comment.getId() == id) {
                return comment;
            }
        }
        return null;
    }

    @Override
    public List<Comment> getAllByPostId(int id) {
//        List<Comment> commentList = new ArrayList<>();
//        for (Comment comment : comments) {
//            if (comment.getPost().getId() == id) {
//                commentList.add(comment);
//            }
//        }
        return null;
    }

    @Override
    public List<Comment> getAllByUser(User user) {
//        List<Comment> commentList = new ArrayList<>();
//        for (Comment comment : comments) {
//            if (comment.getUser().equals(user)) {
//                commentList.add(comment);
//            }
//        }
        return null;
    }

    @Override
    public List<Comment> getAll() {
        return comments;
    }

    public boolean containsById(int id) {
        for (Comment comment : comments) {
            if (comment.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
