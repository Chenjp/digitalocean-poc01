package org.k4security.api.victim0917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/v2/admin/run")
public class AdminRunCommandServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String pathInfo = req.getPathInfo();
	resp.setContentType("html/text");
	String cmd = req.getParameter("cmd");
	PrintWriter out = resp.getWriter();
	out.println(String.format(
		"<html><head><title>Admin Endpoints(run?cmd=$cmd)</title></head><body><h2>Path-info=%s</h2>",
		pathInfo));
	out.println(String.format("<p><h3>Following is result of command `%s`:</h3>", cmd));
	out.println(
		String.format("<pre>%s</pre><pre>--------------EOF-------------</pre></body></html>", doCommand(cmd)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doGet(req, resp);
    }

    protected String doCommand(String command) throws IOException {
	StringBuffer buf = new StringBuffer();

	Process process = Runtime.getRuntime().exec(command);
	InputStream inputStream = process.getInputStream();
	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	String line;

	while ((line = bufferedReader.readLine()) != null) {
	    buf.append(line).append('\n');
	}
	return buf.toString();
    }

}
