package de.se_rwth.industry40.interaction_api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "product")
public class Product {
	@Expose(serialize = true, deserialize = false)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCT_SEQ")
	@SequenceGenerator(name="PRODUCT_SEQ", sequenceName = "product_seq")
	long id;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="product")
	OrderItem orderItem;
	
	public Product(){
		
	}

	public Product(OrderItem orderItem) {
		super();
		this.orderItem = orderItem;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}
