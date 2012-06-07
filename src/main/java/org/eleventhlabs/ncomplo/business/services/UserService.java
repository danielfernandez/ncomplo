package org.eleventhlabs.ncomplo.business.services;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.eleventhlabs.ncomplo.business.entities.League;
import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.entities.repositories.LeagueRepository;
import org.eleventhlabs.ncomplo.business.entities.repositories.UserRepository;
import org.eleventhlabs.ncomplo.business.util.IterableUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    
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
    public boolean authenticate(final String login, final String password) {
        return true;
    }
    
    
    
    @Transactional
    public User find(final String login) {
        return this.userRepository.findOne(login);
    }
    
    
    @Transactional
    public List<User> findAll() {
        final List<User> users = 
                IterableUtils.toList(this.userRepository.findAll());
        Collections.sort(users);
        return users;
    }

    
    
    @Transactional
    public User save(
            final String login,
            final String name,
            final String email,
            final boolean admin,
            final boolean active,
            final List<Integer> leagueIds) {
        
        final boolean userExists = this.userRepository.exists(login);
        
        final User user =
                (!userExists? new User() : this.userRepository.findOne(login));
        
        user.setLogin(login);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(null);
        user.setAdmin(admin);
        user.setActive(active);

        for (final League league : user.getLeagues()) {
            league.getParticipants().remove(user);
        }
        user.getLeagues().clear();
        for (final Integer leagueId : leagueIds) {
            final League league = this.leagueRepository.findOne(leagueId);
            user.getLeagues().add(league);
            league.getParticipants().add(user);
        }
        
        if (!userExists) {
            return this.userRepository.save(user);
        }
        
        return user;
        
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
        
System.out.println(">> NEW PASSWORD FOR " + login + " IS: \"" + newPassword + "\"");
        
        return user;
        
    }
    

    
    
    @Transactional
    public void delete(final String login) {
        this.userRepository.delete(login);
    }

    
    
}
