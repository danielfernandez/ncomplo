package org.eleventhlabs.ncomplo.business.services;

import org.apache.commons.lang.RandomStringUtils;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.entities.repositories.LeagueRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LeagueRepository leagueRepository;

    
    
    public UserService() {
        super();
    }
    
    

    @Transactional
    public boolean authenticate(final String username, final String password) {
        return true;
    }
    

    @Transactional
    public User addUser(final Integer leagueId, final String login, 
            final String email, final boolean admin) {
        
        final User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(null);
        user.setAdmin(admin);
        user.setLeague(this.leagueRepository.findOne(leagueId));
        
        return this.userRepository.save(user);
        
    }

    
    @Transactional
    public User resetPassword(final String login, final boolean sendEmail) {
        
        final User user = this.userRepository.findOne(login);
        final String newPassword = 
                RandomStringUtils.randomAlphanumeric(10);
        
        return null;
        
    }
    
    
}
