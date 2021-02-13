package by.feedblog.api.service;

import by.feedblog.api.dao.repository.BookmarkRepository;
import by.feedblog.api.dao.repository.PostRepository;
import by.feedblog.api.dao.repository.UserRepository;
import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.Token;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private BookmarkRepository bookmarkRepository;
    private PostRepository postRepository;
//    private TokenRepository tokenRepository;


    public boolean save(User user) {
        if (userRepository.existsByFullName(user.getFullName())) {
            throw new IsExistException("User is exist!", user.getUsername(), "save");
        } else {
            userRepository.save(user);
        }
        return true;
    }

    public void bookmark(int userId, int postId){
        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(userId);
        List<Bookmark> bookmarks = user.getBookmarks();
        if(bookmarkRepository.existsByPost(post)){
            throw new IsExistException("Bookmark is exist!", post.getTitle(), "saveBookmark");
        } else {
            bookmarks.add(new Bookmark(user, post));
            userRepository.save(user);
        }
    }

    public void deleteById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("User with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByName(String name) {
        if (userRepository.existsByFullName(name)) {
            userRepository.deleteByFullName(name);
        }
        throw new NotFoundException("User with name not found!", name, "deleteByName");
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(int id) {
        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();
        }
        throw new NotFoundException("User with id not found!", String.valueOf(id), "getById");
    }

    public User getByName(String name) {
        if (userRepository.existsByFullName(name)) {
            return userRepository.findByFullName(name);
        }
        throw new NotFoundException("User with id not found!", name, "getByName");
    }

    public void updateName(int id, String name) {
        if (!userRepository.existsById(id)){
            throw new NotFoundException("User with id not found!", String.valueOf(id), "update");
        }
        User user = userRepository.findById(id).get();
        user.setFullName(name);
        userRepository.save(user);
    }

    public void updatePassword(int id, String password) {
        if (!userRepository.existsById(id)){
            throw new NotFoundException("User with id not found!", String.valueOf(id), "update");
        }
        User user = userRepository.findById(id).get();
        user.setPassword(password);
        userRepository.save(user);
    }

    public void updateAge(int id, int age) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User with id not found!", String.valueOf(id), "update");
        }
        User user = userRepository.findById(id).get();
        user.setAge(age);
        userRepository.save(user);
    }

    public boolean registration(User user) {
        if (userRepository.existsByFullName(user.getUsername())) {
            throw new IsExistException("User is exist!", user.getUsername(), "save");
        } else {
            userRepository.save(user);
        }
        return true;
    }

//    public String authorization(String username, String password) {
//        if (userRepository.existsByFullNameAndPassword(username, password)) {
//            UUID uuid = UUID.randomUUID();
//            Token token = new Token();
//            token.setUuid(uuid);
//            tokenRepository.save(token);
//            return uuid.toString();
//        }
//        return null;
//    }
//
//    public boolean validToken(String uuid) {
//        UUID uuid1 = UUID.fromString(uuid);
//        return tokenRepository.existsByUuid(uuid1);
//    }
}
