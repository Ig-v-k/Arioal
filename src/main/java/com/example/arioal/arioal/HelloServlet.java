package com.example.arioal.arioal;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
  private String message;

  @Override
  public void init() throws ServletException {
	message = "Hello world!";
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
	response.setContentType("text/html");

	// Hello
	PrintWriter out = response.getWriter();
	out.println("<html><body>");
	out.println("<h1>" + message + "</h1>");
	out.println("</body></html>");
  }

  public void destroy() {
  }
}