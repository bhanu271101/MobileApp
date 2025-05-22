package com.example.memo.Entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;



@Entity

@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="USERS", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User {


	@Id
	@Column(name = "user_id")
    public String userId;

   

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getTokens() {
		return tokens;
	}

	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	@Column(name="NAME", nullable=false)
    public String name;

    @Column(name="EMAIL",nullable=false)
    public String email;

    @Column(name="PASSWORD", nullable=false)
   
    public String password;

    @Column(name="PHONENUMBER",nullable=false)
    public String phoneNumber;

    @CreationTimestamp
    @Column(name="CREATED_AT", updatable=false)
    public Date createdAt;

    @UpdateTimestamp
    @Column(name="UPDATED_AT", nullable=false)
    public Date updatedAt;
    

    public boolean enabled=false;
    
    public int tokens=0;



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}



}
