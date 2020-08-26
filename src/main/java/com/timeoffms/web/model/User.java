package com.timeoffms.web.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Validated
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(unique=true)
    @Email
    private String email;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
    private Collection<Team> teams;

    public void addUserRole(Role newRole){
        if(roles == null) roles = new HashSet<>();
        roles.add(newRole);
    }

    private int getAvailableVacationDays(){
        LocalDateTime today = LocalDateTime.now();
        Period age = Period.between(startDate.toLocalDate(), today.toLocalDate());
        int years = age.getYears();
        int months = age.getMonths();

        return years * 12 + months;
    }

    public String getFullname(){
        return this.firstName + " " + this.lastName;
    }
}
