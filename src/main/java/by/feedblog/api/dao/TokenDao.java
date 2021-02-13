package by.feedblog.api.dao;

import java.util.UUID;

public interface TokenDao {
    void add(UUID uuid);

    boolean contains(String uuid);
}
