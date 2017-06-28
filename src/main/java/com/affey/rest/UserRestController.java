/*************************************************************************
 *
 * ADOBE CONFIDENTIAL
 * __________________
 *
 *  Copyright 2012 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 **************************************************************************/
package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.affey.model.User;
import com.affey.rest.pojo.UserPojo;
import com.affey.service.UserService;
import com.google.gson.JsonObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Manage Users", description = "APIs to create, read, update and delete user")
public class UserRestController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);
  
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/v1/api/admin/users", method = POST)
  @ApiOperation(value = "Add new user", position = 1)
  public ResponseEntity<User>  defineUser(
      HttpServletRequest request,
      @ApiParam(value = "User Definition", required = true) @Valid @RequestBody UserPojo userPojo) {

    LOGGER.info("Create user : {}", userPojo.getUserName());
    return new ResponseEntity<User>(userService.create(userPojo),HttpStatus.CREATED);
  }

  @RequestMapping(value = "/v1/api/admin/users", method = GET)
  @ApiOperation(value = "List all users", position = 2)
  public ResponseEntity<List<User>>  listUsers() {
    LOGGER.info("Get all users.");
     return new ResponseEntity<List<User>>(userService.list(),HttpStatus.OK);
  }

  @RequestMapping(value = "/v1/api/admin/users/{userName}", method = GET)
  @ApiOperation(value = "Get a user", position = 3)
  public ResponseEntity<?>  getUser(
      @ApiParam(value = "User Name", required = true) @PathVariable String userName) {
    LOGGER.info("Get user : {}", userName);

    User user= userService.get(userName);
    if(user==null){
		  JsonObject error= new JsonObject();
	    	error.addProperty("error", "No User found with username "+ userName);
	    	return  new ResponseEntity<>(error.toString(),HttpStatus.NOT_FOUND );
	  }
	return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @RequestMapping(value = "/v1/api/admin/users/{userName}", method = PUT)
  @ApiOperation(value = "Update a user", position = 4)
  public ResponseEntity<?>  getUser(
      @ApiParam(value = "User Name", required = true) @PathVariable String userName,
      @ApiParam(value = "User Definition", required = true) @Valid @RequestBody UserPojo userDefinition) {
    LOGGER.info("Update user : {}", userName);
    return new ResponseEntity<User>(userService.update(userName, userDefinition),HttpStatus.ACCEPTED);
  }
  
  @RequestMapping(value = "/v1/api/admin/users/{userName}/enable", method = PUT)
  @ApiOperation(value = "Update to enable/disable a user", position = 5)
  public ResponseEntity<?>  updateStatus(
      @ApiParam(value = "User Name", required = true) @PathVariable String userName,
      @ApiParam(value = "Enabled flag", required = true)@RequestParam(required=true) Boolean enabled) {
    LOGGER.info("Update user status: {}", userName);
    return new ResponseEntity<User>(userService.setEnabled(userName, enabled),HttpStatus.OK);
  }
  
  @RequestMapping(value = "/v1/api/admin/users/{userName}", method = DELETE)
  @ApiOperation(value = "Delete a user", position = 6)
  public ResponseEntity<?>  deleteUser(
      @ApiParam(value = "User Name", required = true) @PathVariable String userName) {

    LOGGER.info("Delete user : {}", userName);
    return new ResponseEntity<>(userService.delete(userName), HttpStatus.OK);
  }

}
