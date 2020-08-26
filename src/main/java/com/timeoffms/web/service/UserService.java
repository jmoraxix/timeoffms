package com.timeoffms.web.service;

import com.timeoffms.web.dao.UserRepository;
import com.timeoffms.web.model.Role;
import com.timeoffms.web.model.User;
import com.timeoffms.web.model.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findById(Long id){
		return userRepository.findById(id).get();
	}

	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}

	public void save(User user){
		userRepository.save(user);
	}

	public void delete(Long id){
		userRepository.deleteById(id);
	}

	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setUsername(registration.getUsername());
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setCountry(registration.getCountry());
		user.setStartDate(registration.getStartDate());
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}
}
