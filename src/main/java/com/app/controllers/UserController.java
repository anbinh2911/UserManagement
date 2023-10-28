package com.app.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.UUIDGenerator;

import com.app.models.User;
import com.app.services.UserService;

@WebServlet("/")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public void init() {
		userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();

		switch (action) {
		case "/list":
			listUser(req, resp);
			break;
		case "/new":
			showNewForm(req, resp);
			break;
		case "/create":
			create(req, resp);
			break;
		case "/edit":
			showEditForm(req, resp);
			break;
		case "/update":
			update(req, resp);
			break;
		case "/delete":
			delete(req, resp);
			break;
		default:
			listUser(req, resp);
		}

	}

	public void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> users = userService.listUser();
		
		req.setAttribute("users", users.isEmpty() ? Collections.emptyList() : users);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("user.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	public void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("create.jsp");
		dispatcher.forward(req, resp);
	}

	public void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("edit.jsp");
		dispatcher.forward(req, resp);
	}

	
	public void create(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		User user = new User();
		user.setId(randomID());
		user.setName(name);
		user.setEmail(email);
		user.setAddress(address);
		userService.create(user);
		
		resp.sendRedirect("list");
	}
	
	private String randomID() {
		return UUID.randomUUID().toString();
	}

	public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setEmail(email);
		user.setAddress(address);
		userService.update(user);
		
		resp.sendRedirect("list");
	}

	public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("id");
		userService.delete(id);
		resp.sendRedirect("list");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
