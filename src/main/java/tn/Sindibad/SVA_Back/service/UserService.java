package tn.Sindibad.SVA_Back.service;

import tn.Sindibad.SVA_Back.model.User;

import java.util.List;

public interface UserService {
    List<User> retrieveAllUsers();
    void deleteUser(String id);
    User updateUser(User u);
    User retrieveUser(String id);
}
