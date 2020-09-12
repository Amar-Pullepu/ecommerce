package com.ecommerce.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.PrePersist;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "lastLogin")
	private Timestamp lastLogin;
	
	@Column(name = "dateJoined", nullable = false)
	private Timestamp dateJoined;
	
	@Column(name = "isAdmin", nullable = false)
	private Boolean isAdmin;
	
	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getUsername()=" + getUsername() + "]";
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = false;
	}

	public User() {
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Timestamp getDateJoined() {
		return dateJoined;
	}
	
	@PrePersist
	public void setDateJoined() {
		this.dateJoined = Timestamp.valueOf(LocalDateTime.now());
	}
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
