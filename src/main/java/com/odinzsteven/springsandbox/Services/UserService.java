package com.odinzsteven.springsandbox.Services;

import com.odinzsteven.springsandbox.Entity.MutableUser;
import com.odinzsteven.springsandbox.Entity.User;

import java.util.Collection;
import java.util.Map;

public interface UserService<U extends User> {
    Collection<U> findAll();

    U create(String name);

    void delete(String name);
    

    int count();

    U first();

    U last();
}
