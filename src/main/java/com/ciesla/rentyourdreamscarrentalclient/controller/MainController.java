package com.ciesla.rentyourdreamscarrentalclient.controller;

import com.ciesla.rentyourdreamscarrentalclient.dto.Account;
import com.ciesla.rentyourdreamscarrentalclient.dto.Car;
import com.ciesla.rentyourdreamscarrentalclient.service.AccountService;
import com.ciesla.rentyourdreamscarrentalclient.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.*;

@Controller
public class MainController {

    private final AccountService accountService;

    private final CarService carService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MainController(AccountService accountService, CarService carService) {
        this.accountService = accountService;
        this.carService = carService;
    }

    @GetMapping("/")
    public String mainPage(ModelMap modelMap, Authentication authentication) {
        List<Car> popularCars = carService.findAllByPopularity();
        if(authentication != null) {
            Account loggedAccount = accountService.findAccountByEmail(authentication.getName());
            modelMap.addAttribute("loggedAccount", loggedAccount);
            if(loggedAccount.getEmail().equals("admin@ryd.com")) {
                modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
            }
        }

        modelMap.addAttribute("popularCars", popularCars);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("account")Account account, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()) {
            return "register";
        }

        String hashedPassword = hashPassword(account.getPassword());
        account.setPassword(hashedPassword);
        accountService.registerAccount(account);
        insertRoles(account.getEmail(), account.getPassword());
        return "redirect:/";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/cars/{type}")
    public String carsPage(@PathVariable("type") String type, ModelMap modelMap) {
        List<Car> cars = carService.findAll();
        String capitalizedType = type.substring(0, 1).toUpperCase() + type.substring(1);
        modelMap.addAttribute("cars", cars);
        modelMap.addAttribute("type", capitalizedType);
        return "index";
    }

    private void insertRoles(String email, String password) {
        String sqlUser = String.format("INSERT INTO users (email, password, enabled) VALUES ('%s', '%s', true)", email, password);
        jdbcTemplate.execute(sqlUser);
        String sqlRole = String.format("INSERT INTO user_roles (email, role) VALUES ('%s', '%s')", email, "ROLE_USER");
        jdbcTemplate.execute(sqlRole);
    }

    private String hashPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
