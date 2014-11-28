package com.bbytes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="users")
public class UserEntity {
	public UserEntity(){
		
	}
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Column(name="FNAME")
	private String fname;
	@Column(name="LNAME")
	private String lname;
	@Column(name="MOBILENO")
	private String mobileno;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ACTIVE")
	private boolean active = false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
