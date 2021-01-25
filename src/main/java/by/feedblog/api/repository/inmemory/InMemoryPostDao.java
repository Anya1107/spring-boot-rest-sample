package by.feedblog.api.repository.inmemory;

import by.feedblog.api.entity.*;
import by.feedblog.api.repository.PostDao;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemoryPostDao implements PostDao {
    private final List<Post> posts = new ArrayList<>();

    private static int incId = 1;

    @Override
    public void save(Post post) {
        post.setId(incId++);
        posts.add(post);
    }

    @Override
    public void deleteById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                posts.remove(post);
                break;
            }
        }
    }

    @Override
    public void deleteByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                posts.remove(post);
                break;
            }
        }
    }

    @Override
    public List<Post> getAll() {
        return posts;
    }

    @Override
    public Post getById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public Post getByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                return post;
            }
        }
        return null;
    }

    @Override
    public List<Post> getAllByUser(User user) {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if (post.getUser().equals(user)) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllByTag(Tag tag) {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if (post.getTag().equals(tag)) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllByCategory(Category category) {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if (post.getCategory().equals(category)) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllChecked() {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if (post.isChecked()) {
                postList.add(post);
                postList.sort(Collections.reverseOrder());
            }
        }
        return postList;
    }

    @Override
    public List<Post> getAllUnchecked() {
        List<Post> postList = new ArrayList<>();
        for (Post post : posts) {
            if (!post.isChecked()) {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public boolean containsById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByTitle(String title) {
        for (Post post : posts) {
            if (post.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateDescription(int id, String description) {
        for (Post post : posts) {
            if (post.getId() == id) {
                post.setDescription(description);
                post.setChecked(false);
            }
        }
    }

    @Override
    public void updateTag(int id, Tag tag) {
        for (Post post : posts) {
            if (post.getId() == id) {
                post.setTag(tag);
                post.setChecked(false);
            }
        }
    }

    @Override
    public void updateCategory(int id, Category category) {
        for (Post post : posts) {
            if (post.getId() == id) {
                post.setCategory(category);
                post.setChecked(false);
            }
        }
    }

}
