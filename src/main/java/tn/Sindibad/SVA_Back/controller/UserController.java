package tn.Sindibad.SVA_Back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.Sindibad.SVA_Back.model.User;
import tn.Sindibad.SVA_Back.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/retrieve-all-users")
    @ResponseBody
    public List<User> getUsers(){
        List<User> list=userService.retrieveAllUsers();
        return list;
    }
    @GetMapping("/retrieve-user/{user-id}")
    @ResponseBody
    public User getUserById( @PathVariable("user-id") String id){
        User U=userService.retrieveUser(id);
        return U;
    }
    @PutMapping("/modify-user")
    @ResponseBody
    public User modifyUser(@RequestBody User u) {
        return userService.updateUser(u);
    }
    @DeleteMapping("/delete/{user-id}")
    @ResponseBody
    public void deleteUser(@PathVariable("user-id") String id){
         userService.deleteUser(id);
    }



}
