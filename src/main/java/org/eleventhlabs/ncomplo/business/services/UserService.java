package org.eleventhlabs.ncomplo.business.services;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.entities.repositories.LeagueRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.UserRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private static Sort SORT_BY_NAME = new Sort("name");

    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Autowired
    private EmailService emailService;
    

    
    
    public UserService() {
        super();
    }
    
    

    
    @Transactional
    public boolean authenticate(final String username, final String password) {
        return true;
    }
    
    
    
    @Transactional
    public List<User> findAllUsersOrderByName() {
        return IterableUtils.toList(this.userRepository.findAll(SORT_BY_NAME));
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
        
        final String newPassword = 
                RandomStringUtils.randomAlphanumeric(10);
        final String hashedNewPassword = 
                this.passwordEncryptor.encryptPassword(newPassword);

        final User user = this.userRepository.findOne(login);
        user.setPassword(hashedNewPassword);
    
        if (sendEmail) {
            this.emailService.sendNewPassword(login, newPassword);
        }
        
        return user;
        
    }
    
    
}
