package com;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginAction extends Action{
	public LoginAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		// login
		String uname=req.getParameter("action");
		if(uname.equals("ishu")) {
			return "login.success";
			
		}
		else {
			return "login.failure";
		}
	}
	

}
