package by.feedblog.api.dao.inmemory;

import by.feedblog.api.entity.Tag;
import by.feedblog.api.dao.TagDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class InMemoryTagDao implements TagDao {
    private final List<Tag> tags = new ArrayList<>();
    private static long incId = 1;

    @Override
    public void save(Tag tag) {
        tag.setId(incId++);
        tags.add(tag);
    }

    @Override
    public void delete(long id) {
        for (Tag tag : tags) {
            if (tag.getId() == id) {
                tags.remove(tag);
                break;
            }
        }
    }

    @Override
    public void delete(String name) {
        for (Tag tag : tags) {
            if (tag.getName().equals(name)) {
                tags.remove(tag);
                break;
            }
        }
    }

    @Override
    public List<Tag> getAll() {
        return tags;
    }

    @Override
    public Tag getById(long id) {
        for (Tag tag : tags) {
            if (tag.getId() == id) {
                return tag;
            }
        }
        return null;
    }

    @Override
    public Tag getByName(String name) {
        for (Tag tag : tags) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        return null;
    }

    @Override
    public boolean containsById(long id) {
        for (Tag tag : tags) {
            if (tag.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByName(String name) {
        for (Tag tag : tags) {
            if (tag.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
