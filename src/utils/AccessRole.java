package utils;

import javax.servlet.http.HttpSession;

import entities.Staff;

public class AccessRole {
	public static boolean isAllow(HttpSession session, int requireRole) {
		try {
			Staff staff = (Staff)session.getAttribute("login");
			if(staff == null){
				return false;
			} else{
				if(staff.getPosition() < requireRole){
					return false;
				} else{
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
