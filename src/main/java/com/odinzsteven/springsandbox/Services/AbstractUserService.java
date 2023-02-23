package com.odinzsteven.springsandbox.Services;

import com.odinzsteven.springsandbox.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class AbstractUserService<U extends User> implements UserService<U> {

    protected final ConcurrentNavigableMap<String, U> users;
    protected final JpaRepository<U, Long> repo;

    protected AbstractUserService(JpaRepository<U, Long> repo) {
        final ConcurrentNavigableMap<String, U> users = new ConcurrentSkipListMap<>();
        for (U user : repo.findAll())
            users.put(user.getName(), user);
        this.users = users;
        this.repo = repo;
    }

    @Override
    public Collection<U> findAll() {
        return users.values();
    }

    @Override
    public void delete(String name) {
        users.computeIfPresent(name, (username, user) -> {
            repo.delete(user);
            return null;
        });
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public U first() {
        Map.Entry<String, U> entry = users.firstEntry();
        return entry == null? null : entry.getValue();
    }

    @Override
    public U last() {
        Map.Entry<String, U> entry = users.lastEntry();
        return entry == null? null : entry.getValue();
    }
}
