package com.ciesla.rentyourdreamscarrentalclient.controller;

import com.ciesla.rentyourdreamscarrentalclient.dto.Account;
import com.ciesla.rentyourdreamscarrentalclient.dto.Car;
import com.ciesla.rentyourdreamscarrentalclient.service.AccountService;
import com.ciesla.rentyourdreamscarrentalclient.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;

    private final CarService carService;

    @Autowired
    public AdminController(AccountService accountService, CarService carService) {
        this.accountService = accountService;
        this.carService = carService;
    }

    @GetMapping("/")
    public String admin() {
        return "redirect:admin/panel";
    }

    @GetMapping("/panel")
    public String adminPanel() {
        return "admin-panel";
    }

    @GetMapping("/cars")
    public String showAllCars(ModelMap modelMap) {
        List<Car> cars = carService.findAll();
        modelMap.addAttribute("cars", cars);
        return "admin-cars";
    }

    @GetMapping("/cars/search")
    public String searchForACar() {
        return "admin-cars-search-form";
    }

    @PostMapping("/cars/search")
    public String searchForACar(@RequestParam("keyword") String keyword, ModelMap modelMap) {
        List<Car> carsFound = carService.findCarsByKeyword(keyword);
        modelMap.addAttribute("cars", carsFound);
        return "admin-cars-search-results";
    }

    @GetMapping("/car")
    public String showCarById(@RequestParam("carId") Integer id, ModelMap modelMap) {
        Car car = carService.findCarById(id);
        if(car.getRentedToUserId() != null) {
            Account account = accountService.findAccountById(car.getRentedToUserId());
            modelMap.addAttribute("account", account);
        }
        modelMap.addAttribute("car", car);
        return "admin-car-detail";
    }

    @GetMapping("/new/car")
    public String addNewCar(ModelMap modelMap) {
        Car car = new Car();
        modelMap.addAttribute("car", car);
        return "admin-new-car";
    }

    @PostMapping("/new/car")
    public String addNewCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "admin-new-car";
        }

        carService.addNewCar(car);
        return "redirect:/admin/panel";
    }

    @GetMapping("/update/car")
    public String updateCar(@RequestParam("carId") Integer id, ModelMap modelMap) {
        Car car = carService.findCarById(id);
        modelMap.addAttribute("car", car);
        return "admin-update-car";
    }

    @PutMapping("/update/car")
    public String updateCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Integer carId = car.getId();
            if(car.getRentedToUserId() == null) {
                car.setAvailability(true);
            }
            return "redirect:/admin/update/car/" + carId;
        }

        carService.updateCar(car);
        return "redirect:/admin/panel";
    }

    @DeleteMapping("/delete/car")
    public String deleteCar(@RequestParam("carId") Integer id) throws Exception {
        carService.deleteCar(id);
        return "redirect:/admin/panel";
    }

    @GetMapping("/accounts")
    public String showAllAccounts(ModelMap modelMap) {
        List<Account> accounts = accountService.findAll();
        modelMap.addAttribute("accounts", accounts);
        return "admin-accounts";
    }

    @GetMapping("/account")
    public String showAccountById(@RequestParam("accountId") Integer id, ModelMap modelMap) {
        Account account = accountService.findAccountById(id);

        modelMap.addAttribute("account", account);
        if(account.getRentedCarId() != null) {
            Car rentedCar = carService.findCarById(account.getRentedCarId());
            modelMap.addAttribute("rentedCar", rentedCar);
        }
        return "admin-account-detail";
    }

    @PostMapping("/rent")
    public String rentCarToUser(HttpServletRequest request) {
        Account account = findAccountByIdFromRequest(request);
        Car car = findCarByIdFromRequest(request);

        account.setRentedCarId(car.getId());
        car.setTimesRented(car.getTimesRented() + 1);
        car.setAvailability(false);
        car.setRentedToUserId(account.getId());
        car.getUserRequests().clear();

        accountService.updateAccount(account);
        carService.updateCar(car);

        return "redirect:/";
    }

    @PostMapping("/return")
    public String returnCar(HttpServletRequest request) {
        Account account = findAccountByIdFromRequest(request);
        Car car = findCarByIdFromRequest(request);

        account.setRentedCarId(null);
        car.setAvailability(true);
        car.setRentedToUserId(null);

        accountService.updateAccount(account);
        carService.updateCar(car);

        return "redirect:/";
    }

    private Car findCarByIdFromRequest(HttpServletRequest request) {
        Integer carId = Integer.parseInt(request.getParameter("carId"));
        return carService.findCarById(carId);
    }

    private Account findAccountByIdFromRequest(HttpServletRequest request) {
        Integer accountId = Integer.parseInt(request.getParameter("accountId"));
        return accountService.findAccountById(accountId);
    }
}
