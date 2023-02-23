package com.odinzsteven.springsandbox.Services;

import com.odinzsteven.springsandbox.Entity.ImmutableUser;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ImmutableUserService extends AbstractUserService<ImmutableUser> {

    public ImmutableUserService(JpaRepository<ImmutableUser, Long> repo) {
        super(repo);
    }

    @Override
    public ImmutableUser create(String name) {
        return users.computeIfAbsent(name, (username) -> repo.save(ImmutableUser.create(name)));
    }

    @Override
    public ImmutableUser rename(String oldName, String newName) {
        for (int i = 0; i < 10; i++) {
            final ImmutableUser oldUser = users.get(oldName);
            final ImmutableUser newUser = new ImmutableUser(oldUser.getId(), newName, oldUser.getVersion());
            final ImmutableUser userWithSameName = users.putIfAbsent(newName, newUser);
            if (userWithSameName == null) {
                try {
                    ImmutableUser savedUser = repo.save(newUser);
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
