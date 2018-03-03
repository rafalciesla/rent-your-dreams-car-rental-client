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
    public String adminPanel(ModelMap modelMap) {
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
        return "admin-panel";
    }

    @GetMapping("/cars")
    public String showAllCars(ModelMap modelMap) {
        List<Car> cars = carService.findAll();
        modelMap.addAttribute("cars", cars);
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
        return "admin-cars";
    }

    @GetMapping("/cars/search")
    public String searchForACar(ModelMap modelMap) {
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
        return "admin-cars-search-form";
    }

    @PostMapping("/cars/search")
    public String searchForACar(@RequestParam("keyword") String keyword, ModelMap modelMap) {
        List<Car> carsFound = carService.findCarsByKeyword(keyword);
        modelMap.addAttribute("cars", carsFound);
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
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
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
        return "admin-car-detail";
    }

    @GetMapping("/new/car")
    public String addNewCar(ModelMap modelMap) {
        Car car = new Car();
        modelMap.addAttribute("car", car);
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
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
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
        return "admin-update-car";
    }

    @PutMapping("/update/car")
    public String updateCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Integer carId = car.getId();
            return "redirect:/admin/update/car?carId=" + carId;
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
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
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
        modelMap.addAttribute("numberOfRequests", carService.getNumberOfRequests());
        return "admin-account-detail";
    }

    @PostMapping("/rent")
    public String rentCarToUser(HttpServletRequest request) {
        performRentalActions(request);
        return "redirect:/admin/panel";
    }

    @PostMapping("/return")
    public String returnCar(HttpServletRequest request) {
        performReturningActions(request);
        return "redirect:/admin/panel";
    }



    private void performReturningActions(HttpServletRequest request) {
        Account account = findAccountByIdFromRequest(request);
        Car car = findCarByIdFromRequest(request);

        account.setRentedCarId(null);
        car.setAvailability(true);
        car.setRentedToUserId(null);

        accountService.updateAccount(account);
        carService.updateCar(car);
    }

    private void performRentalActions(HttpServletRequest request) {
        Account account = findAccountByIdFromRequest(request);
        Car car = findCarByIdFromRequest(request);

        account.setRentedCarId(car.getId());

        car.setTimesRented(car.getTimesRented() + 1);
        car.setAvailability(false);
        car.setRentedToUserId(account.getId());
        car.getUserRequests().remove(account);

        accountService.updateAccount(account);
        carService.updateCar(car);

        accountService.deleteOtherRequestsFromThatAccount(account.getId());
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
