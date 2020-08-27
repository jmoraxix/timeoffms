package com.timeoffms.web.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Validated
@Table(name = "country")
public class Country {

    @Id
    private String code;

    @NotNull
    private String name;

    @NotNull
    private String dialCode;
}
