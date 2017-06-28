package com.affey.security;

import java.util.regex.Matcher;

import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class AetherWebSecurityExpressionRoot extends WebSecurityExpressionRoot {
  
  public AetherWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
		super(a, fi);
		// TODO Auto-generated constructor stub
	}

private static final Pattern TENANTS_API_PATTERN = Pattern.compile(".*/api/tenants/([^/?]+).*");
  
  private static final Logger LOGGER = LoggerFactory.getLogger(AetherWebSecurityExpressionRoot.class);
//
//  private final TenantService tenantService;
//  
//  /**
//   * @param a
//   * @param fi
//   */
//  public AetherWebSecurityExpressionRoot(Authentication a, FilterInvocation fi, TenantService ts) {
//  super(a, fi);
//    this.tenantService = ts;
//  }
//
//  public boolean matchTenantAndAuthority(String authority, String adminAuth) {
//    return matchAdminAuthority(adminAuth) || matchTenantAndAuthority(authority);    
//  }
//
//  public boolean matchAdminAuthority(String adminAuth) {
//    return hasAuthority(adminAuth) && assertOrCreateTenant();
//  }
//  
//  public boolean matchTenantAndAuthority(String authority) {
//    return hasAuthority(authority) && matchTenant();    
//  }
//
//  public boolean assertOrCreateTenant() {
//    final String requestURI = this.request.getRequestURI();    
//    final Matcher matcher = TENANTS_API_PATTERN.matcher(requestURI);
//    if (matcher.matches()) {
//      final String tenant = matcher.group(1);
//      if(tenantService.get(tenant) == null) {
//        LOGGER.info("tenant : {} does not exist, creating it on the fly", tenant);
//        tenantService.create(new TenantDefinition(tenant, tenant, null));
//      }
//    }
//    return true;
//  }
//  
//  public boolean matchTenant() {
//    final String requestURI = this.request.getRequestURI();    
//    final Matcher matcher = TENANTS_API_PATTERN.matcher(requestURI);
//    if (matcher.matches()) {
//      final String tenant = matcher.group(1);
//      final String principal = this.request.getUserPrincipal().getName();
//      return tenant.equalsIgnoreCase(principal);
//    } else {
//      // Tenant name not found in the request, allow
//      return true;
//    }
//  }

}
