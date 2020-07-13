package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper usermapper;
    private final HashService hashService;

    public UserService(UserMapper usermapper, HashService hashService) {
        this.usermapper = usermapper;
        this.hashService = hashService;
    }
    public boolean isUserNameAvailable(String username){
        return usermapper.selectUser(username) == null;
    }

    public int CreateUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return usermapper.insert(new User(null, user.getUsername(), encodedSalt, hashedPassword, user.getFirstname(), user.getLastname()));

    }
    public User getUser(String username) {
        return usermapper.selectUser(username);
    }
}
