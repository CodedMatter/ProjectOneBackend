package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.models.Administrator;

@WebServlet(urlPatterns = "/administrator")
public class AdministratorServelt extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8536506435690804742L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hello");
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Hello Post!!");
		ObjectMapper mapper = new ObjectMapper();
		InputStream reqBody = req.getInputStream();
		Administrator admin = mapper.readValue(reqBody, Administrator.class);
		System.out.println(admin);
	}

}
