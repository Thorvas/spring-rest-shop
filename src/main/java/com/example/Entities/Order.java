/*package com.example.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {
	public Order() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="seller_id")
	private Customer seller;
	
	@ManyToOne
	@JoinColumn(name="buyer_id")
	private Customer buyer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getSeller() {
		return seller;
	}

	public void setSeller(Customer seller) {
		this.seller = seller;
	}

	public Customer getBuyer() {
		return buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}
	
} */
