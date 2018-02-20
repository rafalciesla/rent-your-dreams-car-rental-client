package com.ciesla.rentyourdreamscarrentalclient.controller;

import com.ciesla.rentyourdreamscarrentalclient.dto.Account;
import com.ciesla.rentyourdreamscarrentalclient.dto.Car;
import com.ciesla.rentyourdreamscarrentalclient.service.AccountService;
import com.ciesla.rentyourdreamscarrentalclient.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final CarService carService;

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService, CarService carService) {
        this.accountService = accountService;
        this.carService = carService;
    }

    @GetMapping("/")
    public String accountDetails(Authentication authentication, ModelMap modelMap) {
        String email = authentication.getName();
        Account account = accountService.findAccountByEmail(email);
        if(account.getRentedCarId() != null) {
            Car rentedCar = carService.findCarById(account.getRentedCarId());
            modelMap.addAttribute("rentedCar", rentedCar);
        }
        modelMap.addAttribute("account", account);

        return "account-detail";
    }

    @GetMapping("/rent")
    public String requestRent(@RequestParam("carId") Integer id, Authentication authentication) {
        Car car = carService.findCarById(id);
        Account account = accountService.findAccountByEmail(authentication.getName());
        List<Account> requestList = car.getUserRequests();
        requestList.add(account);
        carService.updateCar(car);

        return "redirect:/";
    }
}
