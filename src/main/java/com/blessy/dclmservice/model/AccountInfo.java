package com.blessy.dclmservice.model;

import com.blessy.dclmservice.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class AccountInfo {

	@Id
	private Long user_id;

	@NotEmpty(message = "last name Required.")
	private String last_name;

	@NotEmpty(message = "first name Required.")
	private String first_name;

	private String other_name;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String profile;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private Account user;
}
