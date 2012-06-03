package org.eleventhlabs.ncomplo.web.admin.controller;

import java.util.List;

import org.eleventhlabs.ncomplo.business.entities.User;
import org.eleventhlabs.ncomplo.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    
    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return this.userService.findAllUsersOrderByName();
    }
    
    
    @RequestMapping("/admin/user/list")
    public String test() {
        return "admin/user/list";
    }
    
    
}
