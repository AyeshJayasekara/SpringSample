package com.saegis.springsample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.saegis.springsample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<com.saegis.springsample.entity.User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            return new User(username, optionalUser.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void registerUser(String username, String password, List<String> authorityList, PasswordEncoder passwordEncoder){
        com.saegis.springsample.entity.User user = new com.saegis.springsample.entity.User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }
}
