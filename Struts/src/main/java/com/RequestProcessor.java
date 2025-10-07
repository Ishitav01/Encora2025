package com;
import java.io.FileInputStream;
import java.util.Properties;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RequestProcessor {
	public void doProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletContext application=request.getServletContext();
			String realpath=application.getRealPath("/WEB-INF/lib/config.properties");
			Properties prop= new Properties();
			prop.load(new FileInputStream(realpath));
			
			String action = request.getParameter("action");

			if (action == null || action.isEmpty()) {
			    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing 'action' parameter in request.");
			    return;
			}

			String actionclass = prop.getProperty(action);
			if (actionclass == null) {
			    response.sendError(HttpServletResponse.SC_NOT_FOUND, "No class found for action: " + action);
			    return;
			}
			Action actionobj=(Action)
					Class.forName(actionclass).getConstructor().newInstance();
			String result=actionobj.execute(request, response);
			String nextpage=prop.getProperty(result);
			RequestDispatcher rd=request.getRequestDispatcher(nextpage);
			rd.forward(request, response);	
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		}
	}


