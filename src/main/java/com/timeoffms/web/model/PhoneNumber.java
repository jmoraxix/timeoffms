package com.timeoffms.web.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Validated
@Table(name = "phone_number")
public class PhoneNumber {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull
    @ManyToOne
    private Country country;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PhoneNumberType phoneNumberType;

    public String getParsedPhoneNumber(){
        return country.getDialCode() + " " + phoneNumber;
    }
}
