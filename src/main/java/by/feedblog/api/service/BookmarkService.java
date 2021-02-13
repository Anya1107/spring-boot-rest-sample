package by.feedblog.api.service;

import by.feedblog.api.dao.repository.BookmarkRepository;
import by.feedblog.api.entity.Bookmark;
import by.feedblog.api.entity.User;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BookmarkService {
    private BookmarkRepository bookmarkRepository;

    public void add(Bookmark bookmark){
        if(bookmarkRepository.existsByPost(bookmark.getPost())){
            throw new IsExistException("Bookmark is exist!", bookmark.getPost().getTitle(), "save");
        }
        bookmarkRepository.save(bookmark);
    }

    public void deleteById(int id){
        if(bookmarkRepository.existsById(id)){
            bookmarkRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Bookmark with id not found!", String.valueOf(id), "deleteById");
    }

    public Bookmark getById(int id){
        if(bookmarkRepository.existsById(id)) return bookmarkRepository.findById(id).get();
        throw new NotFoundException("Bookmark with id not found!", String.valueOf(id), "getById");
    }

    public List<Bookmark> getAll(){
        return bookmarkRepository.findAll();
    }

    public List<Bookmark> getAllByUser(User user){
        return bookmarkRepository.findAllByUser(user);
    }
}
