package org.eleventhlabs.ncomplo.business.services;

import org.eleventhlabs.ncomplo.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    

    public boolean authenticate(final String username, final String password) {
        return true;
    }
    
    
}
