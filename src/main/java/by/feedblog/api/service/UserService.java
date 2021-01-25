package by.feedblog.api.service;


import by.feedblog.api.entity.User;
import by.feedblog.api.repository.TokenDao;
import by.feedblog.api.repository.UserDao;
import by.feedblog.api.service.exception.IsExistException;
import by.feedblog.api.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private UserDao userDao;
    private TokenDao tokenDao;

    public UserService(UserDao userDao, TokenDao tokenDao) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
    }

    public boolean save(User user){
        if(userDao.containsByName(user.getFullName())){
            throw new IsExistException("User is exist!", user.getUsername(), "save");
        } else {
            userDao.save(user);
        }
        return true;
    }

    public void deleteById(int id){
        if(userDao.containsById(id)) userDao.deleteById(id);
        throw new NotFoundException("User with id not found!", String.valueOf(id), "deleteById");
    }

    public void deleteByName(String name){
        if(userDao.containsByName(name)) userDao.deleteByName(name);
        throw new NotFoundException("User with name not found!", name, "deleteByName");
    }

    public List<User> getAll(){
        return userDao.getAll();
    }

    public User getById(int id){
        if(userDao.containsById(id)){
            return userDao.getById(id);
        }
        throw new NotFoundException("User with id not found!", String.valueOf(id), "getById");
    }

    public User getByName(String name){
        if(userDao.containsByName(name)){
            return userDao.getByName(name);
        }
        throw new NotFoundException("User with id not found!", name, "getByName");
    }

    public void updateName(int id, String name){
        if(!userDao.containsById(id)) throw new NotFoundException("User with id not found!", String.valueOf(id), "update");
            userDao.updateName(id, name);
    }

    public void updatePassword(int id, String password){
        if(!userDao.containsById(id)) throw new NotFoundException("User with id not found!", String.valueOf(id), "update");
            userDao.updatePassword(id, password);
    }

    public void updateAge(int id, int age){
        if(!userDao.containsById(id)) throw new NotFoundException("User with id not found!", String.valueOf(id), "update");
            userDao.updateAge(id, age);
    }

    public boolean registration(User user){
        if(userDao.containsByName(user.getUsername())){
            throw new IsExistException("User is exist!", user.getUsername(), "save");
        } else {
            userDao.save(user);
        }
        return true;
    }

    public String authorization(String username, String password){
        if (userDao.authorization(username, password)) {
            UUID uuid = UUID.randomUUID();
            tokenDao.add(uuid);
            return uuid.toString();
        }
        return null;
    }

    public boolean validToken(String uuid){
        return tokenDao.contains(uuid);
    }
}
