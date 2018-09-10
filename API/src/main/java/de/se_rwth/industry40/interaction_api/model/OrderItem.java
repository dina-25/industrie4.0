package de.se_rwth.industry40.interaction_api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "orderItem")
public class OrderItem {
	@Expose(serialize = true, deserialize = false)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDERITEM_SEQ")
	@SequenceGenerator(name="ORDERITEM_SEQ", sequenceName = "orderitem_seq")
	long id;
	@Expose
	@Column(name = "quantity")
	Integer quantity;
	@Expose
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "product_id")
	Product product;
	@ManyToOne(fetch=FetchType.LAZY, targetEntity = Order.class)
	@JoinColumn(name="order_id")
	Order order;
	
	public OrderItem(){
		
	}
		
	public OrderItem(Integer quantity, Product product, Order order) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.order = order;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
