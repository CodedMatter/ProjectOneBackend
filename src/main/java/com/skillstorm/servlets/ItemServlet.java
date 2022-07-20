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
import com.skillstorm.doas.ItemDAO;
import com.skillstorm.doas.ItemImpl;
import com.skillstorm.models.Administrator;
import com.skillstorm.models.Item;

@WebServlet(urlPatterns = "/item/*")
public class ItemServlet extends HttpServlet{

	private static final long serialVersionUID = -954487132744010736L;
	ItemDAO dao = new ItemImpl();
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * /findAll gets all the items
	 * /findById gets an item with that id
	 * /findByName gets items with name that is similar
	 * @return list of items or an item
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");
		
		// default to returning everything 
		if(paths.length == 0) {
			List<Item> admins = dao.findAll();
			resp.setContentType("application/json");
			resp.getWriter().print(mapper.writeValueAsString(admins));
		}
		else {
			switch(paths[1]) {
			case"findAll":
				List<Item> items = dao.findAll();
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(items));
				break;
			case "findById":
				Item item = dao.findById(Integer.parseInt(paths[2]));
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(item));
				break;
			case "findByName":
				List<Item> items1 = dao.findByName(paths[2]);
				resp.setContentType("application/json");
				resp.getWriter().print(mapper.writeValueAsString(items1));
				break;
			}
		}
	}
	
	/**
	 * creates an item
	 * @return the item created
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Item item = mapper.readValue(reqBody, Item.class);
		item = dao.create(item);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(item));
	}
	
	/**
	 * updates an item by id
	 * @return the updated item
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream reqBody = req.getInputStream();
		Item item = mapper.readValue(reqBody, Item.class);
		item = dao.update(item);
		resp.setContentType("application/json");
		resp.getWriter().print(mapper.writeValueAsString(item));
	}
	
	/**
	 * deletes item by the id
	 */
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paths = req.getPathInfo().split("/");

		if(paths.length > 1) {
			dao.delete(Integer.parseInt(paths[1]));
		}
	}
}
