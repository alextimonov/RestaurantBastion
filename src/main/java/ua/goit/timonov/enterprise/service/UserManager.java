package ua.goit.timonov.enterprise.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Alex on 04.10.2016.
 */
@Service
public class UserManager {
    private HashMap<String, User> users;

    public UserManager() {
        users = new HashMap();
        users.put("user", new User("user", "user", "ROLE_USER"));
        users.put("admin", new User("admin", "admin", "ROLE_USER, ROLE_ADMIN"));
    }

    public User getUser(String username) throws UsernameNotFoundException {
        if( !users.containsKey( username ) ){
            throw new UsernameNotFoundException( username + " not found" );
        }

        return users.get( username );
    }
}
