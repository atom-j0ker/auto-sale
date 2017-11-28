package com.auto.controllers;

import com.auto.crud.AutoDAO;
import com.auto.crud.UsersDAO;
import com.auto.model.Auto;
import com.auto.model.UserEmbeddable;
import com.auto.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController extends HttpServlet {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private AutoDAO autoDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String authorizationForm() {
        return "authorization";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(UserEmbeddable userEmbeddable, Users users, BindingResult result, HttpServletRequest request) {

        userEmbeddable.setPassword(passwordEncoder.encode(userEmbeddable.getPassword()));
        users.setUserEmbeddable(userEmbeddable);
        users.setRole("ROLE_USER");

        if (result.hasErrors()) {
            return "registration";
        }
        usersDAO.add(users);

        return "redirect:/";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String myProfile(@PathVariable("userId") long userId, Model model) {
        Users user = usersDAO.get(userId);

        model.addAttribute("user", user);
        model.addAttribute("autos", autoDAO.getAutoListByUser(userId));

        return "my-profile";
    }
}
