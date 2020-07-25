package com.blessy.dclmservice.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int role_id;

	@Column(name = "name", unique = true)
	private String role;
	
	@Column(name = "desc")
	private String desc;

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(String role) {
		this(role, role);
	}

	public Role(String role, String desc) {
		this.role = role;
		this.desc = desc;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.role;
	}

}
