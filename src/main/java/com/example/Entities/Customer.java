package com.example.Entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//Customer entity mapped to database

@Entity
@Table(name="customer")
public class Customer {

	//Class fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="balance")
	private int balance;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;


	@Column(name="email")
	private String email;


	@OneToMany(mappedBy="productOwner", fetch=FetchType.EAGER, orphanRemoval = true)
	private List<Product> ownedProducts;
	
	@OneToOne(mappedBy="customer")
	@JsonIgnore
	private Users user;
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="friends", 
	joinColumns = @JoinColumn(name="customer_id"),
	inverseJoinColumns=@JoinColumn(name="friend_id")
	)
	@JsonIgnore
	private Set<Customer> friends;
	
	public Set<Customer> getFriends() {
		return friends;
	}

	public void setFriends(Set<Customer> friends) {
		this.friends = friends;
	}
	
	public Set<Customer> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(Set<Customer> friendOf) {
		this.friendOf = friendOf;
	}

	@ManyToMany(mappedBy="friends", cascade=CascadeType.PERSIST)
	@JsonIgnore
	private Set<Customer> friendOf;
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public List<Product> getOwnedProducts() {
		return ownedProducts;
	}
	
	public void addProduct(Product newProduct) {
		this.ownedProducts.add(newProduct);
	}
	public void removeProduct(Product newProduct) {
		this.ownedProducts.remove(newProduct);
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}


	public void setOwnedProducts(List<Product> ownedProducts) {
		this.ownedProducts = ownedProducts;
	}

	public Customer() {
		
	}
	
	//Getters and setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
