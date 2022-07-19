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
import com.skillstorm.doas.CompanyDAO;
import com.skillstorm.doas.CompanyImpl;
import com.skillstorm.models.Company;

@WebServlet(urlPatterns = "/company/*")
public class CompanyServlet extends HttpServlet{

	private static final long serialVersionUID = -2009949238591391959L;
	CompanyDAO dao = new CompanyImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * /findAll gets all the companies
	 * /findById gets the company by id
	 * /findByName gets the company by name
	 * @return all companies or a company by the id or name
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");
		
		// default to getting all companies
		if(paths.length == 0) {
			List<Company> admins = dao.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(admins));
		}
		else {
			switch (paths[1]) {
			case "findAll":
				List<Company> companies = dao.findAll();
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(companies));
				break;
			case "findById":
				Company company = dao.findById(Integer.parseInt(paths[2]));
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(company));
				break;
			case "findByName":
				Company company1 = dao.findByName(paths[2]);
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(company1));
				break;
			}
		}
	}
	
	/**
	 * creates a new company
	 * @return the company created
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Company company = mapper.readValue(reqBody, Company.class);
		company = dao.create(company);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(company));
	}
	
	/**
	 * updates a company by id
	 * @return company updated 
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Company company = mapper.readValue(reqBody, Company.class);
		company = dao.update(company);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(company));
	}
	
	/**
	 * deletes a company by id
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");

		if(paths.length > 1) {
			dao.delete(Integer.parseInt(paths[1]));
		}
	}

}
