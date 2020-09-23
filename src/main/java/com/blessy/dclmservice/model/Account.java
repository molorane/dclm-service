package com.blessy.dclmservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@NoArgsConstructor
public class Account implements Serializable{

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
	@JoinTable(name = "account_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<AppRole> roles = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private AccountInfo accountInfo;

	public Account(Long user_id, String username, String email, String password, int status, Set<AppRole> roles) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
		this.roles = roles;
	}

	@PrePersist
	public void prePersist() {
		expiryDate = LocalDate.now().plusMonths(1);
		this.isActive = true;
		this.isLocked = false;
		this.status = 1;
	}

	public boolean accountExpired() {
		return !expiryDate.isAfter(LocalDate.now());
	}

	public boolean hasRole(String userRole){
		return (roles.stream()
				.filter(role -> userRole.equals(role.getRole()))
				.findAny()
				.orElse(null) != null);
	}

}
