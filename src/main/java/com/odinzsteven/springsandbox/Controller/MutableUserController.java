package com.odinzsteven.springsandbox.Controller;

import com.odinzsteven.springsandbox.Entity.MutableUser;
import com.odinzsteven.springsandbox.Services.MutableUserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/mutable/")
public class MutableUserController {

    private final MutableUserService userService;

    public MutableUserController(MutableUserService userService) {
        this.userService = userService;
    }

    @GetMapping("findAll")
    public Collection<MutableUser> findAll() {
        return userService.findAll();
    }

    @GetMapping("rename")
    public MutableUser rename(@RequestParam String oldName, @RequestParam String newName) {
        return userService.rename(oldName, newName);
    }

    @GetMapping("create")
    public MutableUser create(@RequestParam String name) {
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
    public MutableUser first() {
        return userService.first();
    }

    @GetMapping("last")
    public MutableUser last() {
        return userService.last();
    }
}
