package com.odinzsteven.springsandbox.Controller;

import com.odinzsteven.springsandbox.Entity.ImmutableUser;
import com.odinzsteven.springsandbox.Services.ImmutableUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/immutable/")
public class ImmutableUserController {

    private final ImmutableUserService userService;

    public ImmutableUserController(ImmutableUserService userService) {
        this.userService = userService;
    }

    @GetMapping("findAll")
    public Collection<ImmutableUser> findAll() {
        return userService.findAll();
    }

    @GetMapping("rename")
    public ImmutableUser rename(@RequestParam String oldName, @RequestParam String newName) {
        return userService.rename(oldName, newName);
    }

    @GetMapping("create")
    public ImmutableUser create(@RequestParam String name) {
        return userService.create(name);
    }

    @GetMapping("delete")
    public String delete(@RequestParam String name) {
        userService.delete(name);
        return "user deleted: " + name;
    }

    @GetMapping("count")
    public String count() {
        return "mutable users count: " + userService.count();
    }

    @GetMapping("first")
    public ImmutableUser first() {
        return userService.first();
    }

    @GetMapping("last")
    public ImmutableUser last() {
        return userService.last();
    }
}
