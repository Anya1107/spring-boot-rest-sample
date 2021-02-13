package by.feedblog.api.dao.inmemory;

import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.Post;
import by.feedblog.api.entity.User;
import by.feedblog.api.dao.BookmarkDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


public class InMemoryBookmarkDao implements BookmarkDao {
    private List<Bookmark> bookmarks = new ArrayList<>();
    private static int incId = 1;

    @Override
    public void add(Bookmark bookmark) {
        bookmark.setId(incId++);
        bookmarks.add(bookmark);
    }

    @Override
    public void deleteById(int id) {
        for (Bookmark bookmark : bookmarks) {
            if(bookmark.getId() == id){
                bookmarks.remove(bookmark);
                break;
            }
        }
    }

    @Override
    public Bookmark getById(int id) {
        for (Bookmark bookmark : bookmarks) {
            if(bookmark.getId() == id){
                return bookmark;
            }
        }
        return null;
    }

    @Override
    public List<Bookmark> getAll() {
        return bookmarks;
    }

    @Override
    public List<Bookmark> getAllByUser(User user) {
        List<Bookmark> bookmarkList = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            if(bookmark.getUser().equals(user)){
                bookmarkList.add(bookmark);
            }
        }
        return bookmarkList;
    }

    @Override
    public boolean containsByPost(Post post) {
        for (Bookmark bookmark : bookmarks) {
            if(bookmark.getPost().equals(post)) return true;
        }
        return false;
    }

    @Override
    public boolean containsById(int id) {
        for (Bookmark bookmark : bookmarks) {
            if(bookmark.getId() == id) return true;
        }
        return false;
    }
}
