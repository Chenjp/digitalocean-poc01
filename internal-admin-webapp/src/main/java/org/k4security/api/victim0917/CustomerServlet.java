package org.k4security.api.victim0917;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v2/customer/*")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// This is the key: use getPathInfo() to get the rest of the path
	String pathInfo = req.getPathInfo();

	resp.setContentType("application/json");
	PrintWriter out = resp.getWriter();
	out.println(String.format(
		"{\"message\":\"Customer Endpoints(GDPR PII, Biz data, confidential).\",\"path-info\":\"%s\"}",
		pathInfo));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// This is the key: use getPathInfo() to get the rest of the path
	String pathInfo = req.getPathInfo();

	resp.setContentType("application/json");
	PrintWriter out = resp.getWriter();
	out.println(String.format(
		"{\"message\":\"Customer Endpoints(GDPR PII, Biz data, confidential).\",\"path-info\":\"%s\"}",
		pathInfo));
    }
}
