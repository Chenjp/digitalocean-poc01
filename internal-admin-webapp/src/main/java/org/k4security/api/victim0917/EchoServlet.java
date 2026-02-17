package org.k4security.api.victim0917;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    private String convertRequest(HttpServletRequest request) throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream out = new PrintStream(baos);
	out.println("=== Request Line ===");
	out.println(request.getMethod() + " " + request.getRequestURI() + " " + request.getProtocol());

	out.println("\n=== Raw Query String ===");
	out.println(request.getQueryString());

	out.println("\n=== Remote Info ===");
	out.println("Remote Addr: " + request.getRemoteAddr());
	out.println("Remote Host: " + request.getRemoteHost());
	out.println("Remote Port: " + request.getRemotePort());
	out.println("Local Addr: " + request.getLocalAddr());
	out.println("Local Name: " + request.getLocalName());
	out.println("Local Port: " + request.getLocalPort());

	out.println("\n=== X-Forwarded Headers ===");
	out.println("X-Forwarded-For: " + request.getHeader("X-Forwarded-For"));
	out.println("X-Forwarded-Host: " + request.getHeader("X-Forwarded-Host"));
	out.println("X-Forwarded-Proto: " + request.getHeader("X-Forwarded-Proto"));

	out.println("\n=== Headers ===");
	Enumeration<String> headerNames = request.getHeaderNames();
	while (headerNames.hasMoreElements()) {
	    String name = headerNames.nextElement();
	    out.println(name + ": " + request.getHeader(name));
	}

	out.println("\n=== Parameters ===");
	request.getParameterMap().forEach((key, values) -> {
	    out.println(key + ": " + String.join(", ", values));
	});

	out.println("\n=== Cookies ===");
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
	    for (Cookie cookie : cookies) {
		out.println(cookie.getName() + ": " + cookie.getValue());
	    }
	}

	out.println("\n=== Session Info ===");
	HttpSession session = request.getSession(false);
	if (session != null) {
	    out.println("Session ID: " + session.getId());
	    out.println("Creation Time: " + new Date(session.getCreationTime()));
	    out.println("Last Accessed: " + new Date(session.getLastAccessedTime()));
	    Enumeration<String> attrs = session.getAttributeNames();
	    while (attrs.hasMoreElements()) {
		String attr = attrs.nextElement();
		out.println(attr + ": " + session.getAttribute(attr));
	    }
	} else {
	    out.println("No session.");
	}

	out.println("\n=== Body ===");
	BufferedReader reader = request.getReader();
	String line;
	while ((line = reader.readLine()) != null) {
	    out.println(line);
	}

	out.println("\n=== Attributes ===");
	Enumeration<String> attrNames = request.getAttributeNames();
	while (attrNames.hasMoreElements()) {
	    String attr = attrNames.nextElement();
	    out.println(attr + ": " + request.getAttribute(attr));
	}

	out.println("\n== Context ==");
	out.println("Request URL: " + request.getRequestURL().toString());
	out.println("Request URI: " + request.getRequestURI());
	out.println("Context Path: " + request.getContextPath());
	out.println("Servlet Context Path: " + request.getServletContext().getContextPath());
	out.println("Servlet Path: " + request.getServletPath());
	out.println("Path Info: " + request.getPathInfo());

	out.flush();
	return new String(baos.toByteArray());
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = convertRequest(req);
        req.getServletContext().log("Echo Servlet - Request dump:\n"+content);
	PrintWriter out = resp.getWriter();
	out.println(String.format(
		"<html><body><h3>Echo:</h3>"));
	out.println(String.format("<p><pre>%s</pre>\n<pre>--------------EOF-------------</pre></body></html>",
		content));
    }
}
