package com.example.memo.Entity;

import java.time.LocalDateTime;

import com.example.memo.Utility.TokenExperationTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    public String token;
    public LocalDateTime expirationTime;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id",nullable=false,foreignKey = @ForeignKey(name="FK_USER_VERIFY_TOKEN"))
    private User user;
    
    

    public VerificationToken() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}


	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public VerificationToken(String token,User user)
    {
        super();
        this.token=token;
        this.user=user;
        this.expirationTime=TokenExperationTime.calculateExperationTome();
    }
    

    public VerificationToken(String token)
    {
        super();
        this.token=token;
        this.expirationTime=TokenExperationTime.calculateExperationTome();
    }

}
