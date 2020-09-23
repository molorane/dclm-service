package com.blessy.dclmservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name", unique = true)
	private String role;
	
	@Column(name = "desc")
	private String desc;

	public AppRole(String role) {
		this(role, role);
	}

	public AppRole(String role, String desc) {
		this.role = role;
		this.desc = desc;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.role;
	}

}
