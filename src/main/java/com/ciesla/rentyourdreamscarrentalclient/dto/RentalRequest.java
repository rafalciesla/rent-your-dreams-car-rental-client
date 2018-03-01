package com.ciesla.rentyourdreamscarrentalclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequest {

    private Integer id;

    private Integer accountId;
    private String accountEmail;
    private String accountFirstName;
    private String accountLastName;

    private Integer carId;

    public RentalRequest() {
    }

    public RentalRequest(Integer accountId, String accountEmail, String accountFirstName, String accountLastName, Integer carId) {
        this.accountId = accountId;
        this.accountEmail = accountEmail;
        this.accountFirstName = accountFirstName;
        this.accountLastName = accountLastName;
        this.carId = carId;
    }
}
