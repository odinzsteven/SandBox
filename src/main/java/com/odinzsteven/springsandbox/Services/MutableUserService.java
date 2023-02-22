package com.odinzsteven.springsandbox.Services;

import com.odinzsteven.springsandbox.Entity.MutableUser;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface MutableUserService {
    Collection<MutableUser> findAll();

    MutableUser save(MutableUser mutableUser);

    MutableUser addMutableUser(String name);

    void delete(MutableUser mutableUser);

    void deleteAll();
    

    int getCount();

    MutableUser getFirstUser();

    MutableUser getLastUser();

    Collection<MutableUser> findMutableUsers();

    Map.Entry<String, MutableUser>[] getBounds();
}
