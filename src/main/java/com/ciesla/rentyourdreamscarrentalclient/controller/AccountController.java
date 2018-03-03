package com.ciesla.rentyourdreamscarrentalclient.controller;

import com.ciesla.rentyourdreamscarrentalclient.dto.Account;
import com.ciesla.rentyourdreamscarrentalclient.dto.Car;
import com.ciesla.rentyourdreamscarrentalclient.dto.RentalRequest;
import com.ciesla.rentyourdreamscarrentalclient.service.AccountService;
import com.ciesla.rentyourdreamscarrentalclient.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

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
        Set<RentalRequest> carRequestList = car.getUserRequests();

        if(requestFromThatAccountAlreadyExists(carRequestList, account.getId())) {
            return "redirect:/";
        }

        carRequestList.add(new RentalRequest(account.getId(), account.getEmail(), account.getFirstName(), account.getLastName(), car.getId()));

        accountService.updateAccount(account);
        carService.updateCar(car);

        return "redirect:/";
    }

    private boolean requestFromThatAccountAlreadyExists(Set<RentalRequest> requests, Integer accountId) {
        for(RentalRequest request : requests) {
            if(request.getAccountId().equals(accountId)) {
                return true;
            }
        }
        return false;
    }
}
