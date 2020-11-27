/**
 * 
 */
package com.staqo.poc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author admin
 *
 */
@Entity
@Table(name = "GuestUser")
@ToString
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1284070321665073971L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String firstName;
    

    @NotNull
    private String lastName;
    
    @NotNull
    private String phoneNumber;
    
    @NotNull
    private String email;
 
    
    private String photo;
    
    private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}
	
	 @Transient
	 public String getPhotosImagePath() {
	        if (this.photo == null || id == null) return null;
	        return "/user-photos/" + id + "/" + photo;
	 }
	
	

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", photo=" + photo + ", createdDate=" + createdDate + "]";
	}
      
	
    
    
    // standard constructors / setters / getters / toString
}