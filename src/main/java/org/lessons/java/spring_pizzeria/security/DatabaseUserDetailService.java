package org.lessons.java.spring_pizzeria.security;

import java.util.Optional;

import org.lessons.java.spring_pizzeria.model.User;
import org.lessons.java.spring_pizzeria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService{


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){

            throw new UsernameNotFoundException("Nessun utente disponibile con questo username");
        }
        return new DatabaseUserDetails(user.get());
    }

}
