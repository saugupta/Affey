package com.affey.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRoleDTO {

  @Id
  @Column(name = "row_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long rowId;
  
  @Column(name = "user_name")
  private String userName;

  @Column(name = "role_name")
  private String roleName;
  
  public UserRoleDTO() {
  }

  public UserRoleDTO(String userName, String roleName) {
    this.userName = userName;
    this.roleName = roleName;
  }

  public String getuserName() {
    return userName;
  }

  public String getRoleName() {
    return roleName;
  }
  
  public Long getRowId() {
    return rowId;
  }
}
