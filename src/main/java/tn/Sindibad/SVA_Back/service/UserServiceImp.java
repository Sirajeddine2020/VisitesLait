package tn.Sindibad.SVA_Back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.Sindibad.SVA_Back.model.User;
import tn.Sindibad.SVA_Back.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();

    }
    public User retrieveUser(String id){
        User u;
        u=userRepository.findById(Long.parseLong(id)).orElse(null);
        return u;
    }
    public User updateUser(User u){
        // does this user change the password.
        User existsUser = userRepository.findById(u.getId()).get();
        boolean isMatches = encoder.matches(u.getPassword(), existsUser.getPassword());
        if (!isMatches) {
            // update the password.
            String hashPassword = encoder.encode(u.getPassword());
            u.setPassword(hashPassword);
        }
        // persisted user to db.
        return userRepository.save(u);
    }
    public void deleteUser(String id){
        userRepository.deleteById(Long.parseLong(id));
    }
}
