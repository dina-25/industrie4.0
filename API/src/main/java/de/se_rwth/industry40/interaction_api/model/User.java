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
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "user")
public class User {
	@Expose(serialize = true, deserialize = false)
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ")
	@SequenceGenerator(name="USER_SEQ", sequenceName = "user_seq")
	long id;
	@Expose
	@Column(name = "firstname")
	String firstName;
	@Expose
	@Column(name = "lastname")
	String lastName;
	@Expose
	@Column(name = "type")
	UserType type;
	@Expose
	@Column(name = "registrationDate")
	Date registrationDate;
	@Expose
	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	List<Order> order;
	
	public User(){
		this.type = UserType.REGULAR;
		this.registrationDate = new Date();
	}
		
	public User(String firstName, String lastName, UserType type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id){
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

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public List<Order> getOrders() {
		return order;
	}
}
