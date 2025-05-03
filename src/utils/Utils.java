package utils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Staff;

public class Utils {
	public static void go(HttpServletRequest request, HttpServletResponse response, String action, String page) {
		if (page == null) {
			page = "index.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/" + action.toLowerCase() + "/" + page);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void addActionMessage(HttpServletRequest request, String key, String msg) {
		Map<Object, Object> messages;
		if(request.getAttribute("actionMessage") == null){
			messages = new HashMap<>();
		} else{
			messages = (Map<Object, Object>) request.getAttribute("actionMessage");
		}
		messages.put(key, msg);
		request.setAttribute("actionMessage", messages);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> getActionMessage(HttpServletRequest request) {
		return (Map<Object, Object>) request.getAttribute("actionMessage");
	}
		
	public static void setLogin(HttpServletRequest request, Staff staff){
		request.getSession().setAttribute("login", staff);
	}
		
	public static Staff getLogin(HttpSession session){
		return (Staff) session.getAttribute("login");
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response){
		setLogin(request, null);
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/index.dis");
		} catch (Exception e) {
			e.printStackTrace();
		}				
	}

	public static void checkRole(HttpServletRequest request, HttpServletResponse response, int i, String action, String jsp) {
		HttpSession session = request.getSession();
		if(getLogin(session) == null) {
			try {
				response.sendRedirect(request.getContextPath() + "/login.dis");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else{
			if(getLogin(session).getPosition() < i){
				Utils.addActionMessage(request, "errorName", "Restriction");
				Utils.addActionMessage(request, "errorDesc", "This action is not allow for this account. Please contact the administrator for details.");
				Utils.go(request, response, "commons/", "error.jsp");
			} else{
				Utils.go(request, response, action, jsp);
			}
		}
	}
	
	public static void setDto(HttpServletRequest request, String key, Object obj){
		request.setAttribute(key, obj);
	}
	
	public static Date s2D(String date){
		return s2D(date, "yyyy-MM-dd H:mm:ss");
	}
	
	public static Date s2D(String date, String pattern){
		java.util.Date utilDate = null;
        try {
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
            utilDate = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return utilDate;
	}
	public static String d2S(Date date, String pattern){
		if(pattern.equals("short")){
			
		} else if(pattern.equals("date")){
			pattern = "yyyy-MM-dd";
		} else if(pattern.equals("time")){
			pattern = "H:mm:ss";
				
		} else if(pattern.equals("default")){
			pattern = "yyyy-MM-dd H:mm:ss";
		}
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	public static String d2S(Date date){
		return d2S(date, "default");
	}
}
