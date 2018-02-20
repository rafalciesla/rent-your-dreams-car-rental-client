package com.ciesla.rentyourdreamscarrentalclient.service;

import com.ciesla.rentyourdreamscarrentalclient.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rental.service.url}")
    private String rentalServiceUrl;

    public void registerAccount(Account account) {
        restTemplate.postForObject(rentalServiceUrl + "/account/register", account, String.class);
    }

    public List<Account> findAll() {
       return restTemplate.getForObject(rentalServiceUrl + "/account/show/all", List.class);
    }

    public Account findAccountById(Integer id) {
        return restTemplate.getForObject(rentalServiceUrl + "/account/show/" + id, Account.class);
    }

    public Account findAccountByEmail(String email) {
        return restTemplate.getForObject(rentalServiceUrl + "/account/show/email?email=" + email, Account.class);
    }

    public void updateAccount(Account account) {
        restTemplate.put(rentalServiceUrl + "/account/update", account);
    }
}
