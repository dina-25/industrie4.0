package de.se_rwth.industry40.interaction_api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "orders")
public class Order {
	@Expose(serialize = true, deserialize = false)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDER_SEQ")
	@SequenceGenerator(name="ORDER_SEQ", sequenceName = "order_seq")
	long id;
	@Expose
	@ManyToOne(fetch=FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name="user_id")
	User user;
	@Expose
	@Column(name = "orderDate")
	Date orderDate;
	@Expose
	@OneToMany(fetch=FetchType.LAZY, mappedBy="order")
	List<OrderItem> orderItems;
	
	public Order(){
		this.orderDate = new Date();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
