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
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate joiningDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

	private String location;

	@ManyToOne
	@JoinColumn(name= "direct_manager_id")
	private User directManager;

	@ManyToMany
	@ToString.Exclude
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName =
			"id"))
	private List<Team> teams;

	@ManyToMany
	@ToString.Exclude
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	//    @ManyToMany
//    @ToString.Exclude
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @JoinTable(name = "alternate_employee_approver", joinColumns = @JoinColumn(name = "approver_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
//    private List<User> approves;

//    @Transient
//    private Double overtimeExtraDays = 0.0;
//
//    @Transient
//    private int expendedVacationDays = 0;

    public void addUserRole(Role newRole){
        if(roles == null) roles = new ArrayList<>();
        roles.add(newRole);
    }

    public double getAvailableVacationDays(){
        return getTotalVacationDays(); //+ this.overtimeExtraDays - expendedVacationDays;
    }

    private double getTotalVacationDays(){
        Period age = Period.between(joiningDate, LocalDate.now());
        int years = age.getYears();
        int months = age.getMonths();

        return (years * 12) + months;
    }

    public String getFullname(){
        return this.firstName + " " + this.lastName;
    }

	public List<Team> getTeams(){
		return Optional.ofNullable(teams).orElse(new ArrayList<>());
	}

    public boolean isApprover(){
        return this.hasRole("APPROVER");
    }

    public boolean hasRole(String role){
        return this.roles.stream().anyMatch(currRole -> currRole.getName().startsWith(role));
    }

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("User{");
		sb.append("id=").append(id);
		sb.append(", username='").append(username).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", firstName='").append(firstName).append('\'');
		sb.append(", lastName='").append(lastName).append('\'');
		sb.append(", country=").append(country);
		sb.append(", location='").append(location).append('\'');
		sb.append(", directManager=").append(directManager.getFullname());
		sb.append('}');
		return sb.toString();
	}
}
