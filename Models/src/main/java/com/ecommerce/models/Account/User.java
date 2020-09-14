package com.ecommerce.models.Account;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.models.Items.Order;
import com.ecommerce.models.Items.OrderItem;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
@Table(name = "users")
public class User implements UserDetails{
	
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
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<OrderItem> userOrderItems;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Order> orders;
	
	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getUsername()=" + getUsername() + "]";
	}

	public User(String username, String password) {
		userOrderItems = new HashSet<OrderItem>();
		orders = new HashSet<Order>();
		this.username = username;
		this.password = password;
		this.isAdmin = false;
	}

	public User() {
		userOrderItems = new HashSet<OrderItem>();
		orders = new HashSet<Order>();
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(this.getIsAdmin()?"ADMIN":"USER"));
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Set<OrderItem> getUserOrderItems() {
		return userOrderItems;
	}

	public void addUserOrderItem(OrderItem userOrderItem) {
		userOrderItems.add(userOrderItem);
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

}
