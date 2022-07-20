package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.doas.LoginDAO;
import com.skillstorm.doas.LoginImpl;
import com.skillstorm.models.Login;

@WebServlet(urlPatterns = "/login/*")
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 9175493163165776378L;
	LoginDAO dao = new LoginImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Login login = mapper.readValue(reqBody, Login.class);
		login = dao.get(login.getUsername(), login.getPassword());
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(login));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Login login = mapper.readValue(reqBody, Login.class);
		login = dao.create(login);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(login));
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");

		if(paths.length > 1) {
			dao.delete(Integer.parseInt(paths[1]));
		}
	}

}
