package org.k4security.api.victim0917;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/api/*", initParams = {
	@WebInitParam(name = "API_GATEWAY_SECRET_HEADER", value = "X-Api-Gateway-Secret"),
	@WebInitParam(name = "API_GATEWAY_SHARED_SECRET", value = "e1a72324477607f05b6f9cdf50a109ba")
})
public class ApiSecurityFilter implements Filter {

    private String secretHeader;
    private String sharedSecret;

    private boolean success = false;

    public void init(FilterConfig filterConfig) throws ServletException {
	// Read configuration from environment variables
	this.secretHeader = filterConfig.getInitParameter("API_GATEWAY_SECRET_HEADER");
	this.sharedSecret = filterConfig.getInitParameter("API_GATEWAY_SHARED_SECRET");

	success = false;

	// Use a default value if not provided by the environment
	if (this.secretHeader == null || this.secretHeader.isEmpty()) {
	    this.secretHeader = "X-Api-Gateway-Secret";
	}

	// Ensure the critical security parameters are configured
	if (this.sharedSecret == null || this.sharedSecret.isEmpty()) {
	    filterConfig.getServletContext().log("API_GATEWAY_SHARED_SECRET environment variable is not configured.");
	    return;
	}
	this.success = true;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {

	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;

	if (success == false) {
	    System.err.println("Filter failure due to filterConfig error.");
	    httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
	    httpResponse.getWriter().write("Access Denied: Filter failure due to filterConfig error.");
	    return;
	}

	// Check the shared secret from the API gateway header
	String providedSecret = httpRequest.getHeader(this.secretHeader);

	if (this.sharedSecret.equals(providedSecret)) {
	    // Both IP and secret are valid, continue the filter chain
	    chain.doFilter(request, response);
	    return;
	}

	// Handle cases where the secret is invalid
	httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
	httpResponse.getWriter().write("Access Denied: Invalid or missing API Gateway secret.");
    }

    public void destroy() {
	// Cleanup code here.
    }
}
