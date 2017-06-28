package com.affey.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.affey.model.User;

@Entity
@Table(name = "User")
public class UserDTO implements User{

  @Id
  @Column(name = "user_name")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "emailid")
  private String emailId;
  
  public UserDTO() {
  }

  public UserDTO(String userName, String password, String emailId){
	  this.userName=userName;
	  this.password=password;
	  this.emailId=emailId;
	  this.enabled=true;
  }
	  
  public UserDTO(User user) {
    this.userName= user.getUserName();
    this.password = user.getPassword();
    this.emailId=user.getEmailId();
    this.enabled = true;
  }

  @Override
  public String getUserName() {
    return userName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setPassword(String password) {
    this.password = password;

  }

  public void setEmailId(String emailId) {
	    this.emailId = emailId;

	  }
@Override
public String toString(){
	return "UserName:"+ this.userName;
}
@Override
public String getEmailId() {
	return emailId;
}

}