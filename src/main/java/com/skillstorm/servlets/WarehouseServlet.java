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
import com.skillstorm.doas.WarehouseDAO;
import com.skillstorm.doas.WarehouseImpl;
import com.skillstorm.models.Administrator;
import com.skillstorm.models.Warehouse;
@WebServlet(urlPatterns = "/warehouse/*")
public class WarehouseServlet extends HttpServlet{

	private static final long serialVersionUID = -1255978588645665829L;
	WarehouseDAO dao = new WarehouseImpl();
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * /findAll gets all the warehouses
	 * /findById gets a warehouse by id
	 * /findByAdminId get all warehouse under a specific admin
	 * @return list of warehouses or a single warehouse
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] path = req.getPathInfo().split("/");
		
		if(path.length == 0) {
			List<Warehouse> warehouses = dao.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(warehouses));
		}
		else {
			switch (path[1]) {
			case "findAll":
				List<Warehouse> warehouses = dao.findAll();
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(warehouses));
				break;
			case"findById":
				Warehouse warehouse = dao.findById(Integer.parseInt(path[2]));
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(warehouse));
				break;
			case"findByAdminId":
				List<Warehouse> warehouses1 = dao.findByAdminId(Integer.parseInt(path[2]));
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(warehouses1));
				break;
			}
			
		}
	}
	
	/**
	 * create a new warehouse
	 * @return warehouse created
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Warehouse warehouse = mapper.readValue(reqBody, Warehouse.class);
		warehouse = dao.create(warehouse);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(warehouse));
	}
	
	/**
	 * updates a warehouse
	 * @return updated warehouse
	 */
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Warehouse warehouse = mapper.readValue(reqBody, Warehouse.class);
		warehouse = dao.update(warehouse);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(warehouse));
	}
	
	/**
	 * Deletes an warehouse by the id
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");

		if(paths.length > 1) {
			dao.delete(Integer.parseInt(paths[1]));
		}
	}
		
}
