package com.skillstorm.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skillstorm.doas.AdministratorDAO;
import com.skillstorm.doas.AdministratorImpl;
import com.skillstorm.models.Administrator;

@WebServlet(urlPatterns = "/administrator/*")
public class AdministratorServelt extends HttpServlet{

	private static final long serialVersionUID = -8536506435690804742L;
	AdministratorDAO dao = new AdministratorImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	
	/**
	 * /findAll gets all the administrator in the database
	 * /findById gets a single administrator with that id
	 * @return a list of administrators or a single administrator
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] path = req.getPathInfo().split("/");
		
		// default to returning everything 
		if(path.length == 0) {
			List<Administrator> admins = dao.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(admins));
		}
		else {
			switch(path[1]) {
			case "findAll":
				List<Administrator> admins = dao.findAll();
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(admins));
				break;
			case "findById":
				Administrator admin = dao.findById(Integer.parseInt(path[2]));
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(admin));
				break;
			}
		}
	}
	
	/**
	 * Creates an administrator
	 * @return the created administrator
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Administrator admin = mapper.readValue(reqBody, Administrator.class);
		admin = dao.create(admin);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(admin));
	}
	
	
	/**
	 * updates the administrator by the id
	 * @return the updated administrator
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Administrator admin = mapper.readValue(reqBody, Administrator.class);
		admin = dao.update(admin);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(admin));
	}
	
	
	/**
	 * Deletes an administrator by the id
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");

		if(paths.length > 1) {
			dao.delete(Integer.parseInt(paths[1]));
		}
	}

}
