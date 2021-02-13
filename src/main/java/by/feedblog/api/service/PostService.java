package by.feedblog.api.service;

import by.feedblog.api.dao.repository.*;
import by.feedblog.api.entity.*;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;
    private LikeRepository likeRepository;
    private DislikeRepository dislikeRepository;
    private ReactionRepository reactionRepository;

    public boolean save(Post post) {
        if (postRepository.existsByTitle(post.getTitle())) {
            throw new IsExistException("Post is exist!", post.getTitle(), "save");
        } else {
            postRepository.save(post);
        }
        return true;
    }

    public void comment(String comment, int postId) {
        if(!postRepository.existsById(postId)){
            throw new NotFoundException("Post with id is not found", String.valueOf(postId), "saveReaction");
        }
        Post post = postRepository.getOne(postId);
        List<Comment> comments = post.getComments();
        comments.add(new Comment(comment));
        postRepository.save(post);
    }

    public void tag(String tag, int postId) {
        if(!postRepository.existsById(postId)){
            throw new NotFoundException("Post with id is not found", String.valueOf(postId), "saveReaction");
        }
        Post post = postRepository.getOne(postId);
        List<Tag> tags = post.getTag();
        if (tagRepository.existsByName(tag)) {
            throw new IsExistException("Tag is exist!", tag, "saveTag");
        } else {
            tags.add(new Tag(tag));
            postRepository.save(post);
        }
    }

    public void like(int userId, int postId) {
        if(!postRepository.existsById(postId)){
            throw new NotFoundException("Post with id is not found", String.valueOf(postId), "saveReaction");
        }
        if(!userRepository.existsById(userId)){
            throw new NotFoundException("User with id is not found", String.valueOf(userId), "saveReaction");
        }
        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(userId);
        List<Like> likes = post.getLikes();
        List<Dislike> dislikes = post.getDislikes();
        if (likeRepository.existsByUserAndPost(user, post)) {
            if (!dislikeRepository.existsByUserAndPost(user, post)) {
                dislikes.add(new Dislike(user, post));
            }
        } else {
            likes.add(new Like(user, post));
        }
        postRepository.save(post);
    }

    public void dislike(int userId, int postId) {
        if(!postRepository.existsById(postId)){
            throw new NotFoundException("Post with id is not found", String.valueOf(postId), "saveReaction");
        }
        if(!userRepository.existsById(userId)){
            throw new NotFoundException("User with id is not found", String.valueOf(userId), "saveReaction");
        }
        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(userId);
        List<Dislike> dislikes = post.getDislikes();
        dislikes.add(new Dislike(user, post));
        postRepository.save(post);
    }

    public void reaction(int userId, int postId, String name) {
        if(!postRepository.existsById(postId)){
            throw new NotFoundException("Post with id is not found", String.valueOf(postId), "saveReaction");
        }
        if(!userRepository.existsById(userId)){
            throw new NotFoundException("User with id is not found", String.valueOf(userId), "saveReaction");
        }
        Post post = postRepository.getOne(postId);
        User user = userRepository.getOne(userId);
        List<Reaction> reactions = post.getReactions();
        Reaction e = new Reaction(name, user);
        if (reactions.contains(e)) {
            throw new IsExistException("Reaction with user and name is exist!", user.getFullName(), "saveReaction");
        } else {
            reactions.add(e);
            postRepository.save(post);;
        }
    }

    public void deleteById(int id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Post with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByTitle(String title) {
        if (postRepository.existsByTitle(title)) {
            postRepository.deleteByTitle(title);
            return;
        }
        throw new NotFoundException("Post with title not found!", title, "deleteByTitle");
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(int id) {
        if (postRepository.existsById(id)) {
            Post byId = postRepository.findById(id).get();
//            byId.setComments(commentRepository.findAllByPost(id));
//            byId.setLikes(likeRepository.findAllByPost(byId));
//            byId.setDislikes(dislikeRepository.findAllByPost(byId));
            return byId;
        }
        throw new NotFoundException("Post with id not found!", String.valueOf(id), "getById");
    }

    public Post getByTitle(String title) {
        if (postRepository.existsByTitle(title)) {
            return postRepository.findByTitle(title);
        }
        throw new NotFoundException("Post with title not found!", title, "getByTitle");
    }

    public List<Post> getAllByUser(User user) {
        return postRepository.findAllByUser(user);
    }

    public List<Post> getAllByTag(Tag tag) {
        return postRepository.findAllByTag(tag);
    }

    public List<Post> getAllByCategory(Category category) {
        return postRepository.findAllByCategory(category);
    }

    public void updateDescription(int id, String description) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundException("Post with id not found!", String.valueOf(id), "update");
        }
        Post post = postRepository.findById(id).get();
        post.setDescription(description);
        postRepository.save(post);
    }

    public void updateCategory(int id, Category category) {
        if (!postRepository.existsById(id)) {
            throw new NotFoundException("Post with id not found!", String.valueOf(id), "update");
        }
        Post post = postRepository.findById(id).get();
        post.setCategory(category);
        postRepository.save(post);
    }
}
