package com.blessy.dclmservice.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	@NotEmpty(message = "Username required.")
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(unique = true)
    @NotEmpty(message = "Email Required.")
    @Email(message = "Invalid email format")
	private String email;

	@Column(nullable = false)
	@Size(min = 4, message = "Password must at least be 4 characters")
	private String password;
	
	@Transient
	@Size(min = 4, message = "Password must at least be 4 characters")
	private String password_confirm;
	
	@Column(columnDefinition = "int default 1")
	private int status;
	
	private boolean isLocked;
	
	private boolean isActive;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_date", unique = true)
	private LocalDateTime createdDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "expiry_date", unique = true)
	private LocalDate expiryDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private UserInfo userInfo;
	
	public User() {
	}

	@PrePersist
	public void prePersist() {
		expiryDate = LocalDate.now().plusMonths(1);
		this.isActive = true;
		this.isLocked = false;
		this.status = 1;
	}

	public User(Long user_id, String username, String email, String password, int status, Set<Role> roles) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
		this.roles = roles;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword_confirm() {
		return password_confirm;
	}

	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean accountExpired() {
		return !expiryDate.isAfter(LocalDate.now());
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public boolean hasRole(String userRole){
		return (roles.stream()
				.filter(role -> userRole.equals(role.getRole()))
				.findAny()
				.orElse(null) != null);
	}

}
