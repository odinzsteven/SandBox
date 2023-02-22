package com.odinzsteven.springsandbox.Services;

import com.odinzsteven.springsandbox.Entity.MutableUser;
import com.odinzsteven.springsandbox.Repository.MutableUserDao;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class MutableUserServiceImplementation implements MutableUserService {

    private final MutableUserDao mutableUserDao;

    public MutableUserServiceImplementation(MutableUserDao mutableUserDao) {
        this.mutableUserDao = mutableUserDao;
    }

    @Override
    public Collection<MutableUser> findAll() {
        return mutableUserDao.findAll();
    }

    @Override
    public MutableUser save(MutableUser mutableUser) {
        return mutableUserDao.save(mutableUser);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public MutableUser getFirstUser() {
        return null;
    }

    @Override
    public MutableUser getLastUser() {
        return null;
    }

    @Override
    public void delete(MutableUser mutableUser) {
        mutableUserDao.delete(mutableUser);
    }

    @Override
    public void deleteAll() {
        mutableUserDao.deleteAll();
    }

    @Override
    public Collection<MutableUser> findMutableUsers() {
        return new ConcurrentSkipListSet<MutableUser>();
    }

    @Override
    public MutableUser addMutableUser(String name) {
        return null;
    }

    @Override
    public Map.Entry<String, MutableUser>[] getBounds() {
        return new Map.Entry[0];
    }
}
