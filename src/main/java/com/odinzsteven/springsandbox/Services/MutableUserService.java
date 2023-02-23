package com.odinzsteven.springsandbox.Services;

import com.odinzsteven.springsandbox.Entity.MutableUser;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MutableUserService extends AbstractUserService<MutableUser> {

    public MutableUserService(JpaRepository<MutableUser, Long> repo) {
        super(repo);
    }

    @Override
    public MutableUser create(String name) {
        return users.computeIfAbsent(name, (username) -> {
            final MutableUser user = new MutableUser();
            user.setName(username);
            return repo.save(user);
        });
    }

    @Override
    public MutableUser rename(String oldName, String newName) {
        for (int i = 0; i < 10; i++) {
            final MutableUser oldUser = users.get(oldName);
            final MutableUser newUser = new MutableUser(oldUser);
            newUser.setName(newName);
            final MutableUser userWithSameName = users.putIfAbsent(newName, newUser);
            if (userWithSameName == null) {
                try {
                    MutableUser savedUser = repo.save(newUser);
                    users.remove(oldName);
                    return savedUser;
                } catch (ConcurrencyFailureException e) {
                    System.out.println("concurrent user rename!");
                }
            } else {
                throw new RuntimeException("name already exist: " + userWithSameName);
            }
        }

        throw new RuntimeException("could not rename the use after 10 attempts!");
    }
}
