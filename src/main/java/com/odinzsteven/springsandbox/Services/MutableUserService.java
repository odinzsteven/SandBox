package com.odinzsteven.springsandbox.Services;

import com.odinzsteven.springsandbox.Entity.MutableUser;
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
}
