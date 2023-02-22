package com.odinzsteven.springsandbox.Controller;

import com.odinzsteven.springsandbox.Entity.MutableUser;
import com.odinzsteven.springsandbox.Services.MutableUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;



    @RestController
    @RequestMapping("/api/v1/")
    public class Controller {

        private final MutableUserService mutableUserService;
        private final ConcurrentNavigableMap<String, MutableUser> mutableUsers;

        public Controller(MutableUserService mutableUserService) {
            final Collection<MutableUser> allMutableUsers = mutableUserService.findMutableUsers();
            final ConcurrentNavigableMap<String, MutableUser> mutableUsers = new ConcurrentSkipListMap<>();
            for (MutableUser mutableUser : allMutableUsers) {
                mutableUsers.put(mutableUser.getName(), mutableUser);
            }

            this.mutableUsers = mutableUsers;
            this.mutableUserService = mutableUserService;
        }

        @GetMapping("findMutableUsers")
        public Collection<MutableUser> findMutableUsers() {
            return mutableUsers.values();
        }

        @GetMapping("addMutableUser")
        public String addMutableUser(@RequestParam String name) {
            MutableUser addedUser = mutableUsers.computeIfAbsent(name, (username) -> mutableUserService.addMutableUser(name));
            return "user added: " + addedUser;
        }

        @GetMapping("getBounds")
        public String getBounds() {
            Map.Entry<String, MutableUser>[] bounds = mutableUsers.subMap(mutableUsers.firstKey(), true, mutableUsers.lastKey(), true).entrySet().toArray(new Map.Entry[mutableUsers.size()]);
            if (bounds.length > 0) {
                if (bounds.length == 1)
                    return "only one user: " + bounds[0].getValue();
                else
                    return "list of users from: " + bounds[0].getValue() + " to: " + bounds[bounds.length - 1].getValue();
            } else
                return "no users";
        }
    }
