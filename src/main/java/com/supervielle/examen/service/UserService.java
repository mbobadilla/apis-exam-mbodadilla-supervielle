package com.supervielle.examen.service;

import com.supervielle.examen.model.User;
import com.supervielle.examen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User addUser(String username,String password){
        User user = new User(username,passwordEncoder.encode(password));
        return userRepository.save(user);

    }

    public boolean auth(String username,String password){
        User user = new User(username,passwordEncoder.encode(password));
        Optional<User> o =
                userRepository.findUserByUsername(user.getUsername());
        if(o.isPresent()) {
            User u = o.get();
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
               return true;
            } else {
                throw new BadCredentialsException("Bad credentials.");
            }
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }
}
