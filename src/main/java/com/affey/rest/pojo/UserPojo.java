package com.affey.rest.pojo;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

import com.affey.model.User;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserPojo implements User, Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = -4630900146097436168L;
	
	@NotNull
	  @Pattern(regexp="[a-z0-9-]+", message="Please Enter a valid User Name.User name should contain a-z,0-9 and hyphen only.")
	   private String userName;
	  /* Match the password using following rules:
	     *  At least one upper case English letter, (?=.*?[A-Z])
		 *	At least one lower case English letter, (?=.*?[a-z])
	 	 *	At least one digit, (?=.*?[0-9])
		 *	At least one special character, (?=.*?[#?!@$%^&*-])	
		 *	Minimum eight in length .{8,} (with the anchors)
	     */
	  // Made transient for not make it to be serialized
	  @NotNull
	  @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message="Please enter a valid Password. It should contain "
	  		+ "atleast one Uppercase, One Lowercase, One Digit and One Special Charecter with minimum length 8.")
	  private transient String password;
	  
	  @NotNull
	  @Email(message="Please enter a valid EmailId")
	  private String emailId;

	  private Boolean enabled;

	  public UserPojo() {
	  }

	  public UserPojo(String userName, String password) {
	    this.userName = userName;
	    this.password = password;
	  }

	  public UserPojo(User user) {
	    this.userName = user.getUserName();
	  }

	  @Override
	  @ApiModelProperty(required = true, position = 1)
	  public String getUserName() {
	    return userName;
	  }

	  public void setUserName(String UserName) {
	    this.userName = UserName;
	  }

	  @Override
	  @ApiModelProperty(required = true, position = 2)
	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }
	  
	  public Boolean getEnabled() {
	    return enabled;
	  }

	  public void setEnabled(Boolean enabled) {
	    this.enabled = enabled;
	  }

	@Override
	public String getEmailId() {
		return emailId;
	}
	
	  
}
