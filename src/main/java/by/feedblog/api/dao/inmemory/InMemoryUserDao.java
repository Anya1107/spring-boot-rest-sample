package by.feedblog.api.dao.inmemory;

import by.feedblog.api.entity.User;
import by.feedblog.api.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserDao implements UserDao {
    private final List<User> users = new ArrayList<>();

    private static int incId = 1;

    @Override
    public void save(User user) {
        user.setId(incId++);
        users.add(user);
    }

    @Override
    public void deleteById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                break;
            }
        }
    }

    @Override
    public void deleteByName(String name) {
        for (User user : users) {
            if (user.getUsername() == name) {
                users.remove(user);
                break;
            }
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean containsById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByName(String name) {
        for (User user : users) {
            if (user.getUsername().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateName(int id, String name) {
        for (User user : users) {
            if (user == null) break;
            if (user.getId() == id) {
                user.setFullName(name);
                break;
            }
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        for (User user : users) {
            if (user == null) break;
            if (user.getId() == id) {
                user.setPassword(password);
                break;
            }
        }
    }

    @Override
    public void updateAge(int id, int age) {
        for (User user : users) {
            if (user == null) break;
            if (user.getId() == id) {
                user.setAge(age);
                break;
            }
        }
    }

    @Override
    public boolean authorization(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
