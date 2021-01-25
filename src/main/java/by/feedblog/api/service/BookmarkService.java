package by.feedblog.api.service;

import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.User;
import by.feedblog.api.repository.BookmarkDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private BookmarkDao bookmarkDao;

    public BookmarkService(BookmarkDao bookmarkDao) {
        this.bookmarkDao = bookmarkDao;
    }

    public void add(Bookmark bookmark){
        if(bookmarkDao.containsByPost(bookmark.getPost())){
            throw new IsExistException("Bookmark is exist!", bookmark.getPost().getTitle(), "save");
        }
        bookmarkDao.add(bookmark);
    }

    public void deleteById(int id){
        if(bookmarkDao.containsById(id)) bookmarkDao.deleteById(id);
        throw new NotFoundException("Bookmark with id not found!", String.valueOf(id), "deleteById");
    }

    public Bookmark getById(int id){
        if(bookmarkDao.containsById(id)) return bookmarkDao.getById(id);
        throw new NotFoundException("Bookmark with id not found!", String.valueOf(id), "getById");
    }

    public List<Bookmark> getAll(){
        return bookmarkDao.getAll();
    }

    public List<Bookmark> getAllByUser(User user){
        return bookmarkDao.getAllByUser(user);
    }
}
