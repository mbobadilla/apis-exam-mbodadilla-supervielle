package com.supervielle.examen.authentication;

import com.supervielle.examen.model.User;
import com.supervielle.examen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsConfig implements UserDetailsService {

    @Autowired
    UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String[]nameUser=username.split("-");
       Optional<User> u= userRepository.findUserByUsername(nameUser[0]);
       if(u.isPresent() && bCryptPasswordEncoder.matches((CharSequence)nameUser[1],u.get().getPassword())){
           return new org.springframework.security.core.userdetails.User(nameUser[0],nameUser[1], new ArrayList<>());
       }else{
           throw new UsernameNotFoundException(nameUser.toString());
       }



    }
}
