package com.smart.config;

import com.smart.entities.User;
import com.smart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // fetching user from database
        User user = userRepository.getUserByUserName(username);

        if (user == null)
        {
            throw new UsernameNotFoundException("Could not found User !!");
        }

        CustomUserDetails customUserDetails=new CustomUserDetails(user);
        return customUserDetails;
    }
}
