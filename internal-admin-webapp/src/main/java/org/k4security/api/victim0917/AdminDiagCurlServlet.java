package org.k4security.api.victim0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v2/admin/diag-curl")
public class AdminDiagCurlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String diagnoseCurl(String url) {
	if (url == null || url.isEmpty()) {
	    return "Error: URL is missing.";
	}

	try {
	    ProcessBuilder pb = new ProcessBuilder("curl", "-ivk", url);
	    Process process = pb.start();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	    StringBuilder output = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
		output.append(line).append("\n");
	    }
	    int exitCode = process.waitFor();
	    if (exitCode == 0) {
		return "Status: Command executed successfully\n<pre>" + output.toString() + "</pre>";
	    } else {
		return "Error: Command failed with exit code " + exitCode + "\n" + output.toString();
	    }
	} catch (IOException | InterruptedException e) {
	    return "Error: " + e.getMessage();
	}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String pathInfo = req.getPathInfo();
	resp.setContentType("html/text");
	PrintWriter out = resp.getWriter();
	out.println(String.format(
		"<html><head><title>Admin Endpoints(diag-curl?url=$url)</title></head><body><h2>Path-info=%s</h2>",
		pathInfo));
	out.println(String.format("<p><h3>Following is result of command `%s`:</h3>", req.getParameter("url")));
	out.println(String.format(
		"<h3>Request detail:</h3><p><pre>%s</pre><pre>--------------EOF-------------</pre></body></html>",
		diagnoseCurl(req.getParameter("url"))));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
    }
}
