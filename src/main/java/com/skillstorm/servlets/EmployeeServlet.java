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
import com.skillstorm.doas.EmployeeDAO;
import com.skillstorm.doas.EmployeeImpl;
import com.skillstorm.models.Employee;

@WebServlet(urlPatterns = "/employee/*")
public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = -7646414528739055941L;
	EmployeeDAO dao = new EmployeeImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	
	/**
	 * /findAll gets all the employees
	 * /findByFirstName gets all employees with a first name that mostly matches the name
	 * /findByLastName gets all employees with a last name that mostly matches the name
	 * /findByid gets an employee by id
	 * /findByWarehouseId gets an employee by warehouse id
	 * @returns list of employees or an employee
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");
		
		// default is to get all employees
		if(paths.length == 0) {
			List<Employee> employees = dao.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(employees));
		}
		else {
			switch (paths[1]) {
			case "findAll":
				List<Employee> employees = dao.findAll();
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(employees));
				break;
			case "findByFirstName":
				List<Employee> employees1 = dao.findByFirstName(paths[2]);
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(employees1));
				break;
			case "findByLastName":
				List<Employee> employees11 = dao.findByLastName(paths[2]);
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(employees11));
				break;
			case "findById":
				Employee employee = dao.findById(Integer.parseInt(paths[2]));
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(employee));
				break;
			case "findByWarehouseId":
				List<Employee> employees111 = dao.findByWarehouseId(Integer.parseInt(paths[2]));
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(employees111));
				break;
			}
		}
	}
	
	/**
	 * creates an employee
	 * @return the employee created
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Employee employee = mapper.readValue(reqBody, Employee.class);
		employee = dao.create(employee);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(employee));
	}
	
	/**
	 * update an employee
	 * @return the updated employee
	 */
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Employee employee = mapper.readValue(reqBody, Employee.class);
		employee = dao.update(employee);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(employee));
	}
	
	/**
	 * delete the employee by id
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");

		if(paths.length > 1) {
			dao.delete(Integer.parseInt(paths[1]));
		}
	}
}
