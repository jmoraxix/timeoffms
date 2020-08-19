package com.timeoffms.web.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Validated
@Table(name = "phone_number")
public class PhoneNumber {

    @Id
    private User user;

    @NotNull
    @ManyToOne
    private Country country;

    @NotNull
    private String phoneNumber;

    public String getParsedPhoneNumber(){
        return country.getDialCode() + " " + phoneNumber;
    }
}
