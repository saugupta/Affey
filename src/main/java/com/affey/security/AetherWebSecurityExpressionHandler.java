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
package com.affey.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;


@Component
public class AetherWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {
  
//  private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
//  
//  @Autowired
//  private TenantService tenantService;
//  
//  @Override
//  protected SecurityExpressionOperations createSecurityExpressionRoot(
//    Authentication a, FilterInvocation fi) {
//    final WebSecurityExpressionRoot root = new AetherWebSecurityExpressionRoot(a, fi, tenantService);
//    root.setPermissionEvaluator(getPermissionEvaluator());
//    root.setTrustResolver(trustResolver);
//    root.setRoleHierarchy(getRoleHierarchy());
//    return root;
//    
//  }  
//
//  /**
//   * Sets the {@link AuthenticationTrustResolver} to be used. The default is
//   * {@link AuthenticationTrustResolverImpl}.
//   *
//   * @param trustResolver
//   *            the {@link AuthenticationTrustResolver} to use. Cannot be
//   *            null.
//   */
//  public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
//      Assert.notNull(trustResolver, "trustResolver cannot be null");
//      this.trustResolver = trustResolver;
//  }

}
