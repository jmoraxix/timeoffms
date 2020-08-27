package com.timeoffms.web.model;

import com.timeoffms.web.model.listeners.UserJpaCallbacksListener;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@EntityListeners({UserJpaCallbacksListener.class})
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
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

//    @OneToMany
//    @JoinColumn(name="user_id", referencedColumnName="id")
//    private List<Overtime> overtimes;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @ManyToMany
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
    private List<Team> teams;

    @ManyToMany
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "approver", joinColumns = @JoinColumn(name = "approver_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private List<User> approves;

    @Transient
    private Double overtimeExtraDays = 0.0;

    @Transient
    private int expendedVacationDays = 0;

    public void addUserRole(Role newRole){
        if(roles == null) roles = new ArrayList<>();
        roles.add(newRole);
    }

    public double getAvailableVacationDays(){
        return getTotalVacationDays() + this.overtimeExtraDays - expendedVacationDays;
    }

    private double getTotalVacationDays(){
        LocalDateTime today = LocalDateTime.now();
        Period age = Period.between(startDate.toLocalDate(), today.toLocalDate());
        int years = age.getYears();
        int months = age.getMonths();

        return (years * 12) + months;
    }

    public String getFullname(){
        return this.firstName + " " + this.lastName;
    }

    public boolean isApprover(){
        return this.hasRole("APPROVER");
    }

    public boolean hasRole(String role){
        return this.roles.stream().anyMatch(currRole -> currRole.getName().startsWith(role));
    }
}
