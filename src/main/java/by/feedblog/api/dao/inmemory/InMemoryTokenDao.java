package by.feedblog.api.dao.inmemory;

import by.feedblog.api.dao.TokenDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Repository
public class InMemoryTokenDao implements TokenDao {
    private List<UUID> uuids = new ArrayList<>();

    @Override
    public void add(UUID uuid) {
        uuids.add(uuid);
    }

    @Override
    public boolean contains(String uuid) {
        for (UUID uuid1 : uuids) {
            if(uuid1.toString().equals(uuid)) return true;
        }
        return false;
    }
}
